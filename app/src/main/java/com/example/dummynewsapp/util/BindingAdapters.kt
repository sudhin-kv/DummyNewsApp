package com.example.dummynewsapp.util

import android.R
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.set
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.dummynewsapp.articlelist.data.Categories

/**
 * Load an image from a URL into an ImageView using Glide.
 *
 * @param view The ImageView to load the image into.
 * @param url The URL of the image to load.
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("tags")
fun setTags(textView: TextView, tags: List<String>?) {
    textView.text = tags?.joinToString(", ") ?: ""
}

@BindingAdapter("clickableTags")
fun setClickableTags(textView: TextView, tags: List<String>?) {
    if (tags != null) {
        val spannableString = SpannableString(tags.joinToString(", "))
        var start = 0
        for (tag in tags) {
            val end = start + tag.length
            spannableString[start, end] = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // Handle tag click
                    Toast.makeText(widget.context, tag, Toast.LENGTH_SHORT).show()
                }
            }
            start = end + 2 // account for ", "
        }
        textView.text = spannableString
        textView.movementMethod = android.text.method.LinkMovementMethod.getInstance()
    } else {
        textView.text = ""
    }
}

@BindingAdapter("htmlText")
fun setHtmlText(textView: TextView, html: String?) {
    if (html != null) {
        textView.text =
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        textView.text = ""
    }
}

@BindingAdapter("spinnerItems")
fun setSpinnerEntries(spinner: Spinner, entries: List<Categories>?) {
    if (entries != null) {
        val adapter = ArrayAdapter(spinner.context, R.layout.simple_spinner_item, entries.map { it.name })
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}