package com.tutuland.modularsandbox.libraries.utils.image

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.*
import com.squareup.picasso.Target
import com.tutuland.modularsandbox.libraries.utils.isNotNullOrEmpty
import com.tutuland.modularsandbox.libraries.utils.send
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

val defaultConfig = Bitmap.Config.RGB_565

interface ImageLoader {
    fun load(url: String): ImageLoader //for final URLs
    fun load(url: String, imageType: ImageType): ImageLoader //for URLs with URL_DIMENS_PLACEHOLDER
    fun load(
        baseImageUrl: String,
        image: String,
        imageType: ImageType
    ): ImageLoader //for URLs divided in basePath and image

    fun fit(option: Boolean = true): ImageLoader
    fun centerCrop(option: Boolean = true): ImageLoader
    fun centerInside(option: Boolean = true): ImageLoader
    fun circularCrop(option: Boolean = true): ImageLoader
    fun config(config: Bitmap.Config): ImageLoader
    fun placeholder(placeholderDrawableRes: Int): ImageLoader
    fun onErrorUse(errorDrawableRes: Int): ImageLoader
    fun noFade(): ImageLoader
    fun into(imageView: ImageView)
    fun into(imageView: ImageView, onImageLoadResult: PublishSubject<Boolean>)
    fun retrieveBitmap(url: String): Observable<Bitmap>
}

data class ImageLoaderRequest(
    var url: String = "",
    var fit: Boolean = false,
    var centerCrop: Boolean = false,
    var centerInside: Boolean = false,
    var circularCrop: Boolean = false,
    var noFade: Boolean = false,
    var config: Bitmap.Config = defaultConfig,
    var placeholder: Int = 0,
    var onErrorUse: Int = 0
)

class PicassoImageLoader(
    private val context: Context,
    private val bitmapTarget: BitmapTarget = BitmapTarget(),
    private var request: ImageLoaderRequest = ImageLoaderRequest()
) : ImageLoader {

    override fun load(url: String): ImageLoader = apply { request.url = url }

    override fun load(url: String, imageType: ImageType): ImageLoader = apply {
        val dimens: String = getDimensionsFor(context.resources, imageType)
        request.url = url.replace(URL_DIMENS_PLACEHOLDER, dimens) //Try to replace both
            .replace(URL_DIMENS_PLACEHOLDER_LONG, dimens)
    }

    override fun load(baseImageUrl: String, image: String, imageType: ImageType): ImageLoader = apply {
        request.url = baseImageUrl + getDimensionsFor(context.resources, imageType) + image
    }

    override fun fit(option: Boolean): ImageLoader = apply { request.fit = option }

    override fun centerCrop(option: Boolean): ImageLoader = apply { request.centerCrop = option }

    override fun centerInside(option: Boolean): ImageLoader = apply { request.centerInside = option }

    override fun circularCrop(option: Boolean): ImageLoader = apply { request.circularCrop = option }

    override fun config(config: Bitmap.Config): ImageLoader = apply { request.config = config }

    override fun placeholder(placeholderDrawableRes: Int): ImageLoader =
        apply { request.placeholder = placeholderDrawableRes }

    override fun onErrorUse(errorDrawableRes: Int): ImageLoader = apply { request.onErrorUse = errorDrawableRes }

    override fun noFade(): ImageLoader = apply { request.noFade = true }

    override fun into(imageView: ImageView) {
        if (request.url.isEmpty()) return
        getPicasso().into(imageView)
    }

    override fun into(imageView: ImageView, onImageLoadResult: PublishSubject<Boolean>) {
        if (request.url.isEmpty()) {
            onImageLoadResult.onNext(false)
            return
        }

        getPicasso().into(imageView, object : Callback {
            override fun onSuccess() = onImageLoadResult.onNext(true)
            override fun onError() = onImageLoadResult.onNext(false)
        })
    }

    override fun retrieveBitmap(url: String): Observable<Bitmap> {
        if (url.isNotNullOrEmpty()) load(url)
        else return Observable.empty<Bitmap>()
        return bitmapTarget.imageBus.doOnSubscribe { getPicasso().into(bitmapTarget) }
    }

    private fun getPicasso(): RequestCreator {
        var picasso = Picasso.with(context).load(request.url)

        if (request.fit) picasso = picasso.fit()
        if (request.centerCrop) picasso = picasso.centerCrop()
        if (request.centerInside) picasso = picasso.centerInside()
        if (request.circularCrop) picasso = picasso.circularCrop(request.url, request.config)
        if (request.placeholder > 0) picasso = picasso.placeholder(request.placeholder)
        if (request.onErrorUse > 0) picasso = picasso.error(request.onErrorUse)
        if (request.noFade) picasso = picasso.noFade()

        picasso = picasso.config(request.config)

        request = ImageLoaderRequest() //reset request
        return picasso
    }
}

data class BitmapTarget(val imageBus: PublishSubject<Bitmap> = PublishSubject.create()) : Target {
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    override fun onBitmapFailed(errorDrawable: Drawable?) {
        Timber.d("onBitmapFailed")
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        bitmap?.let { imageBus send bitmap }
    }
}

class CircularCropper(
    private val imgUrl: String,
    private val config: Bitmap.Config = defaultConfig
) : Transformation {

    override fun key() = imgUrl

    override fun transform(source: Bitmap): Bitmap {
        val paint = Paint().apply {
            isAntiAlias = true
            shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }

        return Bitmap.createBitmap(source.width, source.height, config).also { output ->
            Canvas(output).drawCircle(halfOf(source.width), halfOf(source.height), halfOf(source.width), paint)
            if (source != output) source.recycle()
        }
    }

    private fun halfOf(size: Int): Float = (size / 2).toFloat()
}

fun RequestCreator.circularCrop(imgUrl: String, config: Bitmap.Config = defaultConfig): RequestCreator =
    transform(CircularCropper(imgUrl, config))