package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String{
    var difference = 0L
    var temp = 0L
    difference = this.time - date.time

    if (difference > 0) {

        when (difference) {
            in 0..SECOND -> return "только что"
            in SECOND..45 * SECOND -> return "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> return "через минуту"
            in 75 * SECOND..45 * MINUTE -> {
                temp = difference / MINUTE
                if (temp == 11L) return "через $temp минут"
                else when (temp % 10) {
                    1L -> return "через $temp минуту"
                    in 2..4 -> return "через $temp минуты"
                    else -> return "через $temp минут"
                }
            }
            in 45 * MINUTE..75 * MINUTE -> return "через час"
            in 75 * MINUTE..22 * HOUR -> {
                temp = difference / HOUR
                when (temp) {
                    1L, 21L -> return "через $temp час"
                    in 2..4, 22L -> return "через $temp часа"
                    else -> return "через  $temp часов"
                }
            }
            in 22 * HOUR..26 * HOUR -> return "через день"
            in 26 * HOUR..360 * DAY -> {
                temp = difference / DAY
                if (temp == 11L) return "через $temp дней"
                else when (temp % 10) {
                    1L -> return "через $temp день"
                    in 2..4 -> return "через $temp дня"
                    else -> return "через $temp дней"
                }
            }
            else -> return "более чем через год"
        }
    }
    else {
        difference = abs(difference)
        when (difference) {
            in 0..SECOND -> return "только что"
            in SECOND..45 * SECOND -> return "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> return "минуту назад"
            in 75 * SECOND..45 * MINUTE -> {
                temp = difference / MINUTE
                if (temp == 11L) return "$temp минут назад"
                else when (temp % 10) {
                    1L -> return "$temp минуту назад"
                    in 2..4 -> return "$temp минуты назад"
                    else -> return "$temp минут назад"
                }
            }
            in 45 * MINUTE..75 * MINUTE -> return "час назад"
            in 75 * MINUTE..22 * HOUR -> {
                temp = difference / HOUR
                when (temp) {
                    1L, 21L -> return "$temp час назад"
                    in 2..4, 22L -> return "$temp часа назад"
                    else -> return "$temp часов назад"
                }
            }
            in 22 * HOUR..26 * HOUR -> return "день назад"
            in 26 * HOUR..360 * DAY -> {
                temp = difference / DAY
                if (temp == 11L) return "$temp дней назад"
                else when (temp % 10) {
                    1L -> return "$temp день назад"
                    in 2..4 -> return "$temp дня назад"
                    else -> return "$temp дней назад"
                }
            }
            else -> return "более года назад"
        }
    }

}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(n: Int): String{
        when(this){
            SECOND -> {
                when(n % 10){
                    0, in 5..9 -> return "$n секунд"
                    1 -> return "$n секунду"
                    in 2..4 -> return "$n секунды"
                    else -> return ""
                }
            }
            MINUTE -> {
                if (n == 11) return "$n минут"
                when(n % 10){
                    0, in 5..9 -> return "$n минут"
                    1 -> return "$n минуту"
                    in 2..4 -> return "$n минуты"
                    else -> return ""
                }
            }
            HOUR -> {
                when(n % 10){
                    1 -> return "$n час"
                    in 2..4 -> return "$n часа"
                    else -> return "$n часов"
                }
            }
            DAY -> {
                if (n == 11) return "$n дней"
                when(n % 10){
                    0, in 5..9 -> return "$n дней"
                    1 -> return "$n день"
                    in 2..4 -> return "$n дня"
                    else -> return ""
                }
            }
        }
    }
}

