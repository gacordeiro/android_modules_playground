package com.tutuland.modularsandbox.libraries.utils.image

import android.content.res.Resources
import com.tutuland.modularsandbox.libraries.utils.R

enum class ImageType {
    DETAIL,
    GRID,
    LIST
}

const val URL_DIMENS_PLACEHOLDER = "/{w}x{h}/"
const val URL_DIMENS_PLACEHOLDER_LONG = "/{width}x{height}/"

fun getDimensionsFor(res: Resources, type: ImageType): String {
    val width: Int
    val height: Int

    when (type) {
        ImageType.DETAIL -> {
            width = res.getDimensionPixelSize(R.dimen.img_picture_width)
            height = res.getDimensionPixelSize(R.dimen.img_picture_height)
        }
        ImageType.GRID -> {
            width = res.getDimensionPixelSize(R.dimen.img_grid_width)
            height = res.getDimensionPixelSize(R.dimen.img_grid_height)
        }
        ImageType.LIST -> {
            width = res.getDimensionPixelSize(R.dimen.img_list_width)
            height = res.getDimensionPixelSize(R.dimen.img_list_height)
        }
    }

    return "/" + width + "x" + height + "/"
}