package com.example.testapp.ui.quiz

import android.annotation.SuppressLint
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

@SuppressLint("SetTextI18n")
fun categoryCard(context: Context, category: String, questionSize: Int): MaterialCardView {
    val cardView = createCardView(context)

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
    sizeCategory.text = "Вопросов: $questionSize"

    centerLinerLayout.addView(inCenterLinerLayout)
    centerLinerLayout.addView(lineLayout)
    centerLinerLayout.addView(sizeCategory)

    val checkBox = createCheckBox(context)

    mainLinerLayout.addView(options)
    mainLinerLayout.addView(centerLinerLayout)
    mainLinerLayout.addView(checkBox)

    cardView.addView(mainLinerLayout)

    return cardView
}

/*private fun createRun(context: Context): MaterialTextView {
    val run = MaterialTextView(context)
    val runLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT,
        2f
    )
    runLayoutParams.rightMargin = 5
    run.layoutParams = runLayoutParams
    run.isClickable = true
    run.text = "▶"
    run.textSize = 24f
    run.gravity = Gravity.CENTER
    run.setOnClickListener {
        val intent = Intent(context, QuestionActivity::class.java)
        intent.putExtra("category", run.text.toString())
        context.startActivity(intent)
    }
    return run
}*/

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

private fun createCardView(context: Context): MaterialCardView {
    val cardView = MaterialCardView(context)
    val cardParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
    )
    cardParams.bottomMargin = dpToPx(10f)
    cardView.layoutParams = cardParams
    cardView.radius = dpToPx(10f).toFloat()
    cardView.id = View.generateViewId()

    return cardView
}
