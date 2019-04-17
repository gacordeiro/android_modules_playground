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
import com.tutuland.modularsandbox.libraries.utils.gone
import com.tutuland.modularsandbox.libraries.utils.show
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.list_activity.*
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

        setSupportActionBar(list_toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
        list_swipe_refresh.setOnRefreshListener { presenter.bind() }
        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun showLoading() {
        if (!list_swipe_refresh.isRefreshing) list_swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        if (list_swipe_refresh.isRefreshing) list_swipe_refresh.isRefreshing = false
    }

    override fun showErrorState() {
        rv_list.gone()
        list_error_state.show()
    }

    override fun hideErrorState() {
        rv_list.show()
        list_error_state.gone()
    }

    override fun display(cards: List<Card.Data>) {
        Timber.d("cards:")
        cards.forEach { Timber.d(it.toString()) }
    }

    override fun proceedToDetails() {
        openDetailsScreen()
    }
}
