package by.dev.mo.ankoapplication.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.LinearLayout.LayoutParams
import by.dev.mo.ankoapplication.model.GithubUser
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class GithubAdapter(val users: List<GithubUser>) : RecyclerView.Adapter<GithubAdapter.ViewHolder>() {
    companion object {
        val userItemId = View.generateViewId()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.githubUserItem?.user = users[position]
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
        AnkoContext.create(parent!!.context, false).run {
            githunUserView {
                id = userItemId
                layoutParams = LayoutParams(parent.layoutParams).apply {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(2)
                    bottomMargin = dip(2)
                }
            }
        }.let {
            ViewHolder(it)
        }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var githubUserItem: GithubUserView?

        init {
            githubUserItem = itemView?.find<GithubUserView>(userItemId)
        }
    }
}

inline fun ViewManager.githunUserView(theme: Int = 0, init: GithubUserView.() -> Unit): GithubUserView {
    return ankoView({ ctx: Context -> GithubUserView(ctx) }, theme) { init() }
}