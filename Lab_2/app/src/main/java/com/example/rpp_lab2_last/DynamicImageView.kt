package com.example.rpp_lab2_last

import android.content.Context
import android.view.View.MeasureSpec
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView


class DynamicImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val d = this.getDrawable()

        if (d != null) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height =
                Math.ceil((width * d.getIntrinsicHeight().toFloat() / d.getIntrinsicWidth()).toDouble())
                    .toInt()
            this.setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
