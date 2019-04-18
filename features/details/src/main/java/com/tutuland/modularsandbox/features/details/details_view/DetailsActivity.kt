package com.tutuland.modularsandbox.features.details.details_view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tutuland.modularsandbox.features.details.CardDetails
import com.tutuland.modularsandbox.features.details.R
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.tracking.Tracker
import com.tutuland.modularsandbox.libraries.utils.image.ImageLoader
import com.tutuland.modularsandbox.libraries.utils.setToolbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.details_activity.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), CardDetails.View {

    @Inject lateinit var tracker: Tracker
    @Inject lateinit var presenter: CardDetails.Presenter
    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        setToolbar(findViewById(R.id.toolbar))
        tracker track Tracker.Event.ScreenView(getString(R.string.details_title))
        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun display(card: Card.Data) {
        details_item_title.text = card.title
        imageLoader.load(card.imageUrl)
            .fit()
            .centerInside()
            .into(details_item_image)
        details_item_description.text = card.description
    }

    override fun warnCardNotFound() {
        Toast.makeText(this, getString(R.string.details_warn_card_not_found), Toast.LENGTH_LONG).show()
        finishAfterTransition()
    }
}
