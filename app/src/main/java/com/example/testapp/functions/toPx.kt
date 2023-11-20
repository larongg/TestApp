package com.example.testapp.functions

import android.content.res.Resources
import android.util.TypedValue

fun dpToPx(dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    ).toInt()
}
