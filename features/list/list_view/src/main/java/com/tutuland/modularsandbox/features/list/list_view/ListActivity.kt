package com.tutuland.modularsandbox.features.list.list_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutuland.modularsandbox.features.list.list_contract.CardList
import com.tutuland.modularsandbox.features.list.list_presentation.ListPresenter
import com.tutuland.modularsandbox.features.list.list_source_got.GotGateway
import com.tutuland.modularsandbox.libraries.actions.Actions.openDetailsScreen
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.data.cards.MemoryCardStorage
import com.tutuland.modularsandbox.libraries.tracking.TimberTracker
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class ListActivity : AppCompatActivity(), CardList.View {

    private val presenter: CardList.Presenter = ListPresenter(
        this,
        GotGateway(MemoryCardStorage(), TimberTracker()),
        AndroidSchedulers.mainThread()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun showLoading() {
        Timber.d("showLoading")
    }

    override fun hideLoading() {
        Timber.d("hideLoading")
    }

    override fun showErrorState() {
        Timber.d("showErrorState")
    }

    override fun hideErrorState() {
        Timber.d("hideErrorState")
    }

    override fun display(cards: List<Card.Data>) {
        Timber.d("cards:")
        cards.forEach { Timber.d(it.toString()) }
    }

    override fun proceedToDetails() {
        openDetailsScreen()
    }
}
