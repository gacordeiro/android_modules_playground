package com.tutuland.modularsandbox.features.details.details_presentation

import com.tutuland.modularsandbox.features.details.CardDetails
import com.tutuland.modularsandbox.libraries.data.cards.Card

class DetailsPresenter(
    private val view: CardDetails.View?,
    private val storage: Card.Storage
) : CardDetails.Presenter {

    override fun bind() {
        view?.apply {
            storage.selectedCard?.let { card ->
                display(card)
            } ?: warnCardNotFound()
        }
    }

    override fun unbind() {} //Nothing to do
}