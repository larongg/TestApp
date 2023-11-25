package com.example.testapp.ui.quiz

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.example.testapp.R
import com.example.testapp.functions.dpToPx
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView

class CategoryCard(context: Context, category: String, questionSize: Int) :
    MaterialCardView(context) {
    private val checkBox = createCheckBox(context)

    fun getChecked(): Boolean {
        return checkBox.isChecked
    }

    init {
        this.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { bottomMargin = dpToPx(10f) }
        this.radius = dpToPx(10f).toFloat()
        this.id = View.generateViewId()

        val mainLinerLayout = LinearLayout(context)
        val mainLinerLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mainLinerLayoutParams.setMargins(dpToPx(5f))
        mainLinerLayout.layoutParams = mainLinerLayoutParams
        mainLinerLayout.orientation = LinearLayout.HORIZONTAL

        val options = ImageView(context)
        options.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            5f
        )
        options.isClickable = true
        options.setImageResource(R.drawable.dots_option_vertical)

        val centerLinerLayout = LinearLayout(context)
        val centerLinerLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1f
        )
        centerLinerLayoutParams.marginStart = dpToPx(5f)
        centerLinerLayoutParams.marginEnd = dpToPx(15f)
        centerLinerLayout.layoutParams = centerLinerLayoutParams
        centerLinerLayout.orientation = LinearLayout.VERTICAL
        centerLinerLayout.isClickable = true

        val inCenterLinerLayout = LinearLayout(context)
        inCenterLinerLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        inCenterLinerLayout.orientation = LinearLayout.HORIZONTAL

        val categoryView = MaterialTextView(context)
        categoryView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        categoryView.text = category
        categoryView.textSize = 20f
        categoryView.setTypeface(null, Typeface.BOLD)

        inCenterLinerLayout.addView(categoryView)

        val lineLayout = createLine(context)

        val sizeCategory = MaterialTextView(context)
        sizeCategory.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        sizeCategory.textSize = 16f
        sizeCategory.text = context.getString(R.string.categorySize, questionSize)

        centerLinerLayout.addView(inCenterLinerLayout)
        centerLinerLayout.addView(lineLayout)
        centerLinerLayout.addView(sizeCategory)

        mainLinerLayout.addView(options)
        mainLinerLayout.addView(centerLinerLayout)
        mainLinerLayout.addView(checkBox)

        this.addView(mainLinerLayout)
    }

    private fun createLine(context: Context): LinearLayout {
        val line = LinearLayout(context)
        line.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(2f)
        )
        line.orientation = LinearLayout.HORIZONTAL
        line.setBackgroundColor(ContextCompat.getColor(context, R.color.main_color))

        return line
    }

    private fun createCheckBox(context: Context): MaterialCheckBox {
        val checkBox = MaterialCheckBox(context)
        val checkBoxParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            5f
        )
        checkBoxParams.marginEnd = dpToPx(10f)
        checkBox.layoutParams = checkBoxParams
        checkBox.scaleX = 2f
        checkBox.scaleY = 2f
        checkBox.minHeight = 0
        checkBox.minWidth = 0

        return checkBox
    }
}