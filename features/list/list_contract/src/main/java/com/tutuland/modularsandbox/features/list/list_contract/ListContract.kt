package com.tutuland.modularsandbox.features.list.list_contract

import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.utils.BindingPresenter
import com.tutuland.modularsandbox.libraries.utils.LoadingView
import io.reactivex.Single

interface CardList {
    interface Source {
        fun getCards(): Single<List<Card.Data>>
    }

    interface View : LoadingView {
        infix fun display(cards: List<Card.Data>)
    }

    interface Presenter : BindingPresenter {
        infix fun clickOn(card: Card.Data)
    }
}