package com.tutuland.modularsandbox.libraries.data.cards

import com.tutuland.modularsandbox.libraries.utils.enumerateString
import timber.log.Timber

interface Card {
    interface Storage {
        var listCache: List<Data>
        var selectedCard: Data?
    }

    data class Data(
        val title: String,
        val imageUrl: String,
        val description: String
    )
}

class MemoryCardStorage : Card.Storage {
    override var listCache: List<Card.Data> = emptyList()
        get() {
            Timber.d("getListCache: ${field.enumerateString()}")
            return field
        }
        set(value) {
            field = value
            Timber.d("setListCache: ${field.enumerateString()}")
        }

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