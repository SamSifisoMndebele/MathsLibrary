package com.ssmnd.util

object Util {
    operator fun Number.plus(number: Number) : Number {
        return this.toDouble() + number.toDouble()
    }
    operator fun Number.minus(number: Number) : Number {
        return this.toDouble() - number.toDouble()
    }
    operator fun Number.times(number: Number) : Number {
        return this.toDouble() * number.toDouble()
    }
    operator fun Number.div(number: Number) : Number {
        return this.toDouble() / number.toDouble()
    }
}