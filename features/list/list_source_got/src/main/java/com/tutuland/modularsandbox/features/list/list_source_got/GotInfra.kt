package com.tutuland.modularsandbox.features.list.list_source_got

import com.tutuland.modularsandbox.features.list.list_contract.CardList
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.tracking.Tracker
import io.reactivex.Single

class GotGateway(
    private val storage: Card.Storage,
    private val tracker: Tracker
) : CardList.Source {
    override fun getCards(): Single<List<Card.Data>> = Single.just(
        listOf(
            Card.Data(
                "Jon Snow",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT01_124.jpg",
                """
                    Houses: Stark | Targaryen
                    Titles: Lord Commander of the Night's Watch
                            King of the North
                            Aegon Targaryen, the Sixth of his name, Heir to the Iron Throne
                    Played By: Kit Harington
                """.trimIndent()
            ),
            Card.Data(
                "Daenerys Targaryen",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT01_160.jpg",
                """
                    Houses: Targaryen
                    Titles: Daenerys of the House Targaryen, the First of Her Name,
                            The Unburnt, Queen of the Andals, the Rhoynar and the First Men,
                            Queen of Meereen, Khaleesi of the Great Grass Sea,
                            Protector of the Realm, Lady Regent of the Seven Kingdoms,
                            Breaker of Chains and Mother of Dragons
                    Played By: Emilia Clarke
                """.trimIndent()
            ),
            Card.Data(
                "Tyrion Lannister",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT15_2.jpg",
                """
                    Houses: Lanister
                    Titles: Hand of the King
                            Master of Coin Lord of Casterly Rock
                            Hand of the Queen
                    Played By: Peter Dinklage
                """.trimIndent()
            )
        )
    )

    override fun select(card: Card.Data) {
        storage.selectedCard = card
        tracker track Tracker.Event.UserInteraction("card", "selected", card.title)
    }
}