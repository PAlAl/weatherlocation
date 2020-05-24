package ru.test.weather.common

import android.content.Context
import ru.test.weather.domain.models.DisplayRes

fun Float.format(digits: Int) = if (this == this.toLong().toFloat()) "%d".format(this.toLong()) else "%.${digits}f".format(this)

fun <T : Enum<*>> T.getDisplayResource(context: Context): String {
    val value: Annotation? = this.javaClass.getField(this.name).getAnnotation(DisplayRes::class.java)

    return when (value) {
        is DisplayRes -> if (value.resId != 0) context.getString(value.resId) else ""
        else -> ""
    }
}