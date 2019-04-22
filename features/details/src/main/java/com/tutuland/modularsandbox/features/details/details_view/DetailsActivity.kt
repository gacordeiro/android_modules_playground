package com.tutuland.modularsandbox.features.details.details_view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tutuland.modularsandbox.features.details.CardDetails
import com.tutuland.modularsandbox.features.details.R
import com.tutuland.modularsandbox.libraries.actions.Actions
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.tracking.Tracker
import com.tutuland.modularsandbox.libraries.utils.image.ImageLoader
import com.tutuland.modularsandbox.libraries.utils.plusAssign
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.details_activity.*
import timber.log.Timber
import javax.inject.Inject


class DetailsActivity : AppCompatActivity(), CardDetails.View {

    @Inject lateinit var tracker: Tracker
    @Inject lateinit var presenter: CardDetails.Presenter
    @Inject lateinit var imageLoader: ImageLoader
    private val imageBus: PublishSubject<Boolean> = PublishSubject.create()
    private val sub = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        postponeEnterTransition()
        sub.clear()
        sub += imageBus.subscribe(
            { startPostponedEnterTransition() },
            Timber::e
        )

        intent?.getStringExtra(Actions.DETAILS_EXTRA_TITLE_ID)?.let { details_item_title.transitionName = it }
        intent?.getStringExtra(Actions.DETAILS_EXTRA_IMAGE_ID)?.let { details_item_image.transitionName = it }

        tracker track Tracker.Event.ScreenView(getString(R.string.details_title))
        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun display(card: Card.Data) {
        details_item_title.text = card.title
        imageLoader.load(card.imageUrl)
            .fit()
            .centerInside()
            .into(details_item_image, imageBus)
        details_item_description.text = card.description
    }

    override fun warnCardNotFound() {
        Toast.makeText(this, getString(R.string.details_warn_card_not_found), Toast.LENGTH_LONG).show()
        supportFinishAfterTransition()
    }
}
