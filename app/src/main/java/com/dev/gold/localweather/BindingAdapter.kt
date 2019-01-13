package com.dev.gold.localweather

import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class BindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("app:srcVector")
        fun loadImage(view: ImageView, icon: String) {
            Glide
                .with(view)
                .load(
                    when (icon) {
                        "sn" -> ContextCompat.getDrawable(view.context, R.drawable.ic_sn)
                        "sl" -> ContextCompat.getDrawable(view.context, R.drawable.ic_sl)
                        "h" -> ContextCompat.getDrawable(view.context, R.drawable.ic_h)
                        "t" -> ContextCompat.getDrawable(view.context, R.drawable.ic_t)
                        "hr" -> ContextCompat.getDrawable(view.context, R.drawable.ic_hr)
                        "lr" -> ContextCompat.getDrawable(view.context, R.drawable.ic_lr)
                        "s" -> ContextCompat.getDrawable(view.context, R.drawable.ic_s)
                        "hc" -> ContextCompat.getDrawable(view.context, R.drawable.ic_hc)
                        "lc" -> ContextCompat.getDrawable(view.context, R.drawable.ic_lc)
                        "c" -> ContextCompat.getDrawable(view.context, R.drawable.ic_c)
                        else -> null
                    }
                )
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("app:convertDoubleToInt")
        fun convertDoubleToInt(view: TextView, value: Double) {
            view.text = value.toInt().toString()
        }

        @JvmStatic
        @BindingAdapter("app:toBold", "app:unit", "app:spanColor")
        fun textToBold(view: TextView, value: Double, unit: String, spanColor: Int) {
            val temp = value.toInt().toString() + unit
            textToBoldImpl(view, temp, unit, spanColor)
        }

        @JvmStatic
        @BindingAdapter("app:toBold", "app:unit", "app:spanColor")
        fun textToBold(view: TextView, value: String, unit: String, spanColor: Int) {
            val temp = value + unit
            textToBoldImpl(view, temp, unit, spanColor)
        }

        @JvmStatic
        private fun textToBoldImpl(view: TextView, str: String, unit: String, color: Int) {
            val spannableString = SpannableString(str)
            spannableString.setSpan(AbsoluteSizeSpan(15, true), 0, str.length, SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(ForegroundColorSpan(color), 0, str.length - unit.length, SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, str.length - unit.length, 0)
            view.text = spannableString
        }

    }

}