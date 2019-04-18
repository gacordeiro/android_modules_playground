package com.tutuland.modularsandbox.features.list.list_source_got

import com.tutuland.modularsandbox.features.list.CardList
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
                    Titles: Aegon Targaryen, the Sixth of his name,
                    Heir to the Iron Throne,
                    King of the North,
                    Lord Commander of the Night's Watch
                """.trimIndent()
            ),
            Card.Data(
                "Daenerys Targaryen",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT01_160.jpg",
                """
                    Houses: Targaryen
                    Titles: Daenerys Targaryen, the First of Her Name,
                    The Unburnt, Queen of the Andals, the Rhoynarand
                    and the First Men, Queen of Meereen, Mother of Dragons,
                    Khaleesi of the Great Grass Sea, Breaker of Chains,
                    Lady Regent of the Seven Kingdoms, Protector of the Realm
                """.trimIndent()
            ),
            Card.Data(
                "Tyrion Lannister",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT15_2.jpg",
                """
                    Houses: Lanister
                    Titles: Hand of the King,
                    Master of Coin Lord of Casterly Rock,
                    Hand of the Queen
                """.trimIndent()
            ),
            Card.Data(
                "Sansa Stark",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT01_147.jpg",
                """
                    Houses: Stark
                    Titles: Lady of Winterfell,
                    Warden of the North,
                    Lady of Dreadfort,
                    Lady of Riverrun
                """.trimIndent()
            ),
            Card.Data(
                "Gregor Clegane",
                "http://lcg-cdn.fantasyflightgames.com/got2nd/GT04_49.jpg",
                """
                    Houses: Clegane
                    Titles: Ser Knight of Clegane's Keep,
                    The Mountain That Rides,
                    Mad Dog
                """.trimIndent()
            )
        )
    )

    override fun select(card: Card.Data) {
        storage.selectedCard = card
        tracker track Tracker.Event.UserInteraction("card", "selected", card.title)
    }
}