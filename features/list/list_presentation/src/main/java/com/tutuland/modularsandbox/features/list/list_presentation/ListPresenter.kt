package com.tutuland.modularsandbox.features.list.list_presentation

import com.tutuland.modularsandbox.features.list.CardList
import com.tutuland.modularsandbox.libraries.data.cards.Card
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class ListPresenter(
    private val view: CardList.View?,
    private val source: CardList.Source,
    private val scheduler: Scheduler,
    private val subs: CompositeDisposable = CompositeDisposable()
) : CardList.Presenter {

    override fun bind() {
        retrieveCardList()
    }

    override fun unbind() {
        subs.clear()
    }

    override fun clickOn(card: Card.Data) {
        source select card
        view?.proceedToDetails()
    }

    private fun retrieveCardList() {
        view?.apply {
            showLoading()
            subs.clear()
            subs.add(
                source.getCards()
                    .observeOn(scheduler)
                    .subscribe(::onListReceived, ::onErrorReceived)
            )
        }
    }

    private fun onListReceived(list: List<Card.Data>) {
        view?.apply {
            hideLoading()
            if (list.isNotEmpty()) {
                hideErrorState()
                display(list)
            } else showErrorState()
        }
    }

    private fun onErrorReceived(error: Throwable) {
        Timber.e("onErrorReceived: $error")
        onListReceived(emptyList())
    }
}