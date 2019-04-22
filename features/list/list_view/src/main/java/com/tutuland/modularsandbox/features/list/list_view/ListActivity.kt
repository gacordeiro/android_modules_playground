package com.tutuland.modularsandbox.features.list.list_view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutuland.modularsandbox.features.list.CardList
import com.tutuland.modularsandbox.libraries.actions.Actions
import com.tutuland.modularsandbox.libraries.actions.Actions.detailsScreenIntent
import com.tutuland.modularsandbox.libraries.data.cards.Card
import com.tutuland.modularsandbox.libraries.tracking.Tracker
import com.tutuland.modularsandbox.libraries.utils.gone
import com.tutuland.modularsandbox.libraries.utils.image.ImageLoader
import com.tutuland.modularsandbox.libraries.utils.inflate
import com.tutuland.modularsandbox.libraries.utils.setToolbar
import com.tutuland.modularsandbox.libraries.utils.show
import dagger.android.AndroidInjection
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_activity.*
import kotlinx.android.synthetic.main.list_item.*
import javax.inject.Inject

class ListActivity : AppCompatActivity(), CardList.View {

    @Inject lateinit var tracker: Tracker
    @Inject lateinit var presenter: CardList.Presenter
    @Inject lateinit var imageLoader: ImageLoader
    private val adapter: ListAdapter = ListAdapter()

    private lateinit var titleView: View
    private lateinit var titleId: String
    private lateinit var imageView: View
    private lateinit var imageId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        setToolbar(findViewById(R.id.toolbar))
        tracker track Tracker.Event.ScreenView(getString(R.string.list_title))

        rv_list.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_columns))
        rv_list.adapter = adapter

        list_swipe_refresh.setOnRefreshListener { presenter.requestRefresh() }
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

    override fun proceedToDetails() = detailsScreenIntent().let { intent ->
        intent.putExtra(Actions.DETAILS_EXTRA_TITLE_ID, titleId)
        intent.putExtra(Actions.DETAILS_EXTRA_IMAGE_ID, imageId)
        ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair.create(titleView, titleId),
            Pair.create(imageView, imageId)
        ).let { options -> startActivity(intent, options.toBundle()) }
    }

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
                list_item_title.transitionName = "T:${model.hashCode()}"
                list_item_image.transitionName = "I:${model.hashCode()}"
                imageLoader.load(model.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(list_item_image)

                list_item_card.setOnClickListener {
                    titleId = "T:${model.hashCode()}"
                    titleView = list_item_title
                    imageId = "I:${model.hashCode()}"
                    imageView = list_item_image
                    presenter clickOn model
                }
            }
        }
    }
}
