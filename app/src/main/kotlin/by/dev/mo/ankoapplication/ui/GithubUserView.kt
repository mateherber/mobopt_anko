package by.dev.mo.ankoapplication.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import by.dev.mo.ankoapplication.R
import by.dev.mo.ankoapplication.model.GithubUser
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import org.jetbrains.anko.*

class GithubUserView(ctx: Context) : _LinearLayout(ctx) {
    init {
        orientation = LinearLayout.HORIZONTAL
    }

    var user: GithubUser? = null
        set(value) {
            field = value
            removeAllViews() // TODO refresh
            initView()
        }

    private fun initView() { // TODO create component
        imageView {
            Glide.with(context).load(
                user?.avatarUrl ?: "https://cdn3.iconfinder.com/data/icons/flat-icons-web/40/Refresh-128.png").asBitmap().centerCrop().into(
                object : BitmapImageViewTarget(this) {
                    override fun setResource(resource: Bitmap) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources,
                            resource)
                        circularBitmapDrawable.isCircular = true
                        this@imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
        }.lparams {
            width = dip(40)
            height = dip(40)
            gravity = Gravity.CENTER
            marginStart = dip(15)
            marginEnd = dip(20)
        }
        textView {
            text = user?.login ?: "Loading"
            textSize = 20f
            textColor = ContextCompat.getColor(context, R.color.textColor)
        }.lparams {
            marginEnd = dip(20)
            weight = 1f
            width = matchParent
        }
        context.configuration(orientation = Orientation.LANDSCAPE) {
//            textView {
//                text = "id: ${user?.id?.toString() ?: "Loading"}"
//                textSize = 20f
//                textColor = Color.GREEN
//            }.lparams {
//                marginEnd = dip(20)
//            }
            include<TextView>(R.layout.landscape_textview) {
                text = "id: ${user?.id?.toString() ?: "Loading"}"
            }
        }
        button {
            text = "VISIT"
            background.setColorFilter(ContextCompat.getColor(context, R.color.darkBlue), PorterDuff.Mode.MULTIPLY)
            setOnClickListener {
                if (user == null) {
                    return@setOnClickListener
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(user?.htmlUrl))
                context.startActivity(browserIntent)
            }
        }.lparams {
            marginEnd = dip(15)
        }
    }
}