package com.tutuland.modularsandbox.features.details

import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.utils.BindingPresenter

interface CardDetails {
    interface View {
        infix fun display(card: Card.Data)
        fun warnCardNotFound()
    }

    interface Presenter : BindingPresenter
}