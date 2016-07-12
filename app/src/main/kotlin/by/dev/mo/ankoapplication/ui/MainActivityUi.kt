package by.dev.mo.ankoapplication.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import by.dev.mo.ankoapplication.R
import by.dev.mo.ankoapplication.R.style.ThemeOverlay_AppCompat_Dark_ActionBar
import by.dev.mo.ankoapplication.activity.MainActivity
import by.dev.mo.ankoapplication.service.GithubClient
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivityUi : AnkoComponent<MainActivity>, AnkoLogger {
    private lateinit var progressLayout: View
    private lateinit var contentLayout: RecyclerView
    private var githubAdapter: GithubAdapter? = null

    override fun createView(ui: AnkoContext<MainActivity>): View = ui.run {
        verticalLayout {
            appBarLayout {
                toolbar(ThemeOverlay_AppCompat_Dark_ActionBar) {
                    title = "Github Users"
                    backgroundResource = R.color.lightBlue
                    ui.owner.setSupportActionBar(this)
                }
            }.lparams {
                width = matchParent
            }
            progressLayout = frameLayout {
                progressBar().lparams {
                    gravity = Gravity.CENTER
                }
            }.lparams {
                width = matchParent
                height = matchParent
            }
            contentLayout = recyclerView {
                visibility = View.GONE
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
            }.lparams {
                width = matchParent
                height = matchParent
            }
            refreshItems()
        }
    }

    fun refreshItems() {
        githubAdapter = null
        setLayoutState()
        GithubClient.githubService.listUsers(Math.floor(Math.random() * 500).toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
            githubAdapter = GithubAdapter(it)
            contentLayout.adapter = githubAdapter
            setLayoutState()
        }, {
            error("Could not get Github results", it)
        })
    }

    private fun setLayoutState() {
        if (githubAdapter == null) {
            progressLayout.visibility = View.VISIBLE
            contentLayout.visibility = View.GONE
        } else {
            progressLayout.visibility = View.GONE
            contentLayout.visibility = View.VISIBLE
        }
    }
}

fun Menu.inflateMenu(ctx: Context, ui: MainActivityUi): Boolean {
    with(AnkoContext.create(ctx)) {
        add(0, 1, 0, "Refresh")?.apply {
            setIcon(R.drawable.ic_refresh_white_24dp)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            setOnMenuItemClickListener {
                ui.refreshItems()
                true
            }
        }
    }
    return true
}