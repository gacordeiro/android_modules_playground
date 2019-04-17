package com.tutuland.modularsandbox.features.list.list_view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutuland.modularsandbox.features.list.list_contract.CardList
import com.tutuland.modularsandbox.features.list.list_presentation.ListPresenter
import com.tutuland.modularsandbox.features.list.list_source_got.GotGateway
import com.tutuland.modularsandbox.libraries.actions.Actions.openDetailsScreen
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.data.cards.MemoryCardStorage
import com.tutuland.modularsandbox.libraries.tracking.TimberTracker
import com.tutuland.modularsandbox.libraries.tracking.Tracker
import com.tutuland.modularsandbox.libraries.utils.gone
import com.tutuland.modularsandbox.libraries.utils.image.ImageLoader
import com.tutuland.modularsandbox.libraries.utils.image.PicassoImageLoader
import com.tutuland.modularsandbox.libraries.utils.inflate
import com.tutuland.modularsandbox.libraries.utils.show
import com.tutuland.modularsandbox.libraries.utils.uiScheduler
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_activity.*
import kotlinx.android.synthetic.main.list_item.*

class ListActivity : AppCompatActivity(), CardList.View {

    private val tracker: Tracker = TimberTracker()

    private val presenter: CardList.Presenter = ListPresenter(
        this,
        GotGateway(MemoryCardStorage(), tracker),
        uiScheduler
    )
    private val imageLoader: ImageLoader = PicassoImageLoader(this)

    private val adapter: ListAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)
        tracker track Tracker.Event.ScreenView(getString(R.string.list_title))

        setSupportActionBar(list_toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }

        rv_list.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_columns))
        rv_list.adapter = adapter

        list_swipe_refresh.setOnRefreshListener { presenter.bind() }
        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun showLoading() {
        if (!list_swipe_refresh.isRefreshing) list_swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        if (list_swipe_refresh.isRefreshing) list_swipe_refresh.isRefreshing = false
    }

    override fun showErrorState() {
        rv_list.gone()
        list_error_state.show()
    }

    override fun hideErrorState() {
        rv_list.show()
        list_error_state.gone()
    }

    override fun display(cards: List<Card.Data>) = adapter display cards

    override fun proceedToDetails() = openDetailsScreen()

    inner class ListAdapter(
        private val models: MutableList<Card.Data> = mutableListOf()
    ) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.list_item))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder bind models[position]

        override fun getItemCount(): Int = models.size

        infix fun display(cards: List<Card.Data>) {
            models.clear()
            models.addAll(cards)
            notifyDataSetChanged()
        }

        inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
            infix fun bind(model: Card.Data) {
                list_item_title.text = model.title
                imageLoader.load(model.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(list_item_image)

                list_item_card.setOnClickListener { presenter clickOn model }
            }
        }
    }
}
