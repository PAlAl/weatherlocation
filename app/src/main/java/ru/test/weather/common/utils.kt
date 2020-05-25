package ru.test.weather.common

import android.content.Context
import android.util.TypedValue
import androidx.core.content.ContextCompat
import ru.test.weather.domain.models.DisplayRes

fun Float.format(digits: Int) = if (this == this.toLong().toFloat()) "%d".format(this.toLong()) else "%.${digits}f".format(this)

fun <T : Enum<*>> T.getDisplayResource(context: Context): String {
    val value: Annotation? = this.javaClass.getField(this.name).getAnnotation(DisplayRes::class.java)

    return when (value) {
        is DisplayRes -> if (value.resId != 0) context.getString(value.resId) else ""
        else -> ""
    }
}

fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.colorFromAttr(colorAttr: Int): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(colorAttr, typedValue, true)

    return this.color(typedValue.resourceId)
}