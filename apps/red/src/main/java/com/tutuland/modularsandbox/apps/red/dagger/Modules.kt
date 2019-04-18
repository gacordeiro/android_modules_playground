package com.tutuland.modularsandbox.apps.red.dagger

import android.content.Context
import com.tutuland.modularsandbox.apps.red.RedApp
import com.tutuland.modularsandbox.features.list.CardList
import com.tutuland.modularsandbox.features.list.list_presentation.ListPresenter
import com.tutuland.modularsandbox.features.list.list_source_pokemon.PokemonGateway
import com.tutuland.modularsandbox.features.list.list_view.ListActivity
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.data.cards.MemoryCardStorage
import com.tutuland.modularsandbox.libraries.tracking.AppTracker
import com.tutuland.modularsandbox.libraries.tracking.TimberTracker
import com.tutuland.modularsandbox.libraries.tracking.Tracker
import com.tutuland.modularsandbox.libraries.utils.image.ImageLoader
import com.tutuland.modularsandbox.libraries.utils.image.PicassoImageLoader
import com.tutuland.modularsandbox.libraries.utils.ioScheduler
import com.tutuland.modularsandbox.libraries.utils.uiScheduler
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.pokemontcg.Pokemon
import javax.inject.Singleton

@Module(
    includes = [
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class,
        CardStorageModule::class,
        ImageLoaderModule::class,
        TrackerModule::class
    ]
)
class RedAppModule(private val app: RedApp) {
    @Provides @Singleton
    fun provideContext(): Context = app
}

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [CardListModule::class])
    abstract fun contributeListActivity(): ListActivity
}

@Module
class CardListModule {
    @Provides
    fun provideSource(storage: Card.Storage, tracker: Tracker): CardList.Source =
        PokemonGateway(Pokemon(), storage, tracker, ioScheduler)

    @Provides
    fun provideView(view: ListActivity): CardList.View = view

    @Provides
    fun providePresenter(view: CardList.View, source: CardList.Source): CardList.Presenter =
        ListPresenter(view, source, uiScheduler)
}

@Module
class CardStorageModule {
    @Provides @Singleton
    fun provideCardStorage(): Card.Storage = MemoryCardStorage()
}

@Module
class ImageLoaderModule {
    @Provides @Singleton
    fun provideImageLoader(context: Context): ImageLoader = PicassoImageLoader(context)
}

@Module
class TrackerModule {
    @Provides @Singleton
    fun provideTracker(): Tracker = AppTracker(
        listOf(TimberTracker()) //Add all desired trackers here
    )
}