package com.tutuland.modularsandbox.libraries.data.cards

import timber.log.Timber

interface Card {
    interface Storage {
        var selectedCard: Data?
    }

    data class Data(
        val title: String,
        val imageUrl: String,
        val description: String
    )
}

class MemoryCardStorage : Card.Storage {
    override var selectedCard: Card.Data? = null
        get() {
            Timber.d("getSelectedCard: $field")
            return field
        }
        set(value) {
            field = value
            Timber.d("setSelectedCard: $value")
        }
}