package com.tutuland.modularsandbox.features.list

import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.utils.BindingPresenter
import com.tutuland.modularsandbox.libraries.utils.ErrorStateView
import com.tutuland.modularsandbox.libraries.utils.LoadingView
import io.reactivex.Single

interface CardList {
    interface Source {
        fun getCards(): Single<List<Card.Data>>
        infix fun select(card: Card.Data)
    }

    interface View : LoadingView, ErrorStateView {
        infix fun display(cards: List<Card.Data>)
        fun proceedToDetails()
    }

    interface Presenter : BindingPresenter {
        infix fun clickOn(card: Card.Data)
    }
}