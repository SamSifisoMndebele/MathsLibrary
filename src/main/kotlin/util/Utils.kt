package utils

import org.jetbrains.annotations.Contract
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

object Utils {
    @Contract("_, _ -> new")
    fun toScientific(number: Double, decimals: Int): SciAns {
        val isNegative = number < 0

        var d = if (isNegative) -number else number
        var e = 0
        var m = d.toInt()

        if (m < 1) {
            while (m < 1 || m > 9) {
                d *= 10.0
                e--
                m = d.toInt()
            }
        } else {
            while (m < 1 || m > 9) {
                d /= 10.0
                e++
                m = d.toInt()
            }
        }

        val fixed = min(decimals.toDouble(), 15.0)
        val dec: Double = 10.0.pow(fixed)

        val numUp = dec * (if (isNegative) -d else d)
        var num = numUp.toLong()
        if (numUp > 0) {
            if (numUp - num >= 0.49999) {
                num++
            }
        } else {
            if (num - numUp >= 0.49999) {
                num--
            }
        }
        val string1 = (num / dec).toString() + "<small>×10<sup>" + e + "</sup></small>"
        val string2 = (num / dec).toString() + "E" + e

        return SciAns(string1, string2, string2.toDouble())
    }

    fun fixTo(number: Double, decimals: Int): String {
        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = decimals
        numberFormat.minimumFractionDigits = decimals
        return numberFormat.format(number).replace(',', '.')
    }

    fun roundTo(number: Double, decimals: Int): String {
        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = decimals
        return numberFormat.format(number).replace(',', '.')
    }

    fun toFraction(doubleNumber: Double): Fraction {
        val tolerance = 5.0E-13

        val isNegative = doubleNumber < 0

        val decimalNumber = if (isNegative) -doubleNumber else doubleNumber

        var numerator = 1.0
        var num = 0.0
        var denominator = 0.0
        var den = 1.0
        var number = decimalNumber
        do {
            val numberFloor = floor(number)

            var temp = numerator
            numerator = numberFloor * numerator + num
            num = temp

            temp = denominator
            denominator = numberFloor * denominator + den
            den = temp
            number = 1 / (number - numberFloor)
        } while (abs(decimalNumber - numerator / denominator) > decimalNumber * tolerance)

        return Fraction((if (isNegative) -numerator else numerator).toLong(), denominator.toLong(), doubleNumber)
    }

    @JvmStatic
    fun toFractionString(decimalNumber: Double): String {
        val fraction = toFraction(decimalNumber)

        val numerator = fraction.numerator.toString()
        if (fraction.denominator.toDouble() == 1.0) {
            return numerator
        } else {
            val denominator = fraction.denominator.toString()
            if (numerator.length > 4 || denominator.length > 4) {
                return fraction.decimalNumber.toString()
            }
            return fraction.numerator.toString() + "/" + fraction.denominator
        }
    }

    fun toFractionLatex(decimalNumber: Double): String {
        var fraction = toFraction(decimalNumber)
        val isNegative = fraction.isNegative
        if (isNegative) fraction = fraction.negate()

        val latex = StringBuilder()
        if (isNegative) latex.append('-')
        if (fraction.denominator.toDouble() == 1.0) {
            latex.append(fraction.numerator)
        } else {
            latex.append("\\frac{")
                .append(fraction.numerator)
                .append("}{")
                .append(fraction.denominator)
                .append("}")
        }

        return latex.toString()
    }

    data class SciAns(val text: String, val textE: String, val number: Double)
    class Fraction(numerator: Long, denominator: Long, val decimalNumber: Double) {
        @Contract(" -> new")
        fun negate(): Fraction {
            return if (denominator > 0) Fraction(-numerator, denominator, -decimalNumber)
            else Fraction(numerator, -denominator, -decimalNumber)
        }

        val isNegative: Boolean
            get() = (numerator < 0 && denominator > 0
                    || numerator > 0 && denominator < 0)
        val isPositive: Boolean
            get() = numerator > 0 && denominator > 0
        val isZero: Boolean
            get() = numerator == 0L
        val isNaN: Boolean
            get() = numerator == 0L && denominator == 0L || java.lang.Double.isNaN(decimalNumber)
        val numerator: Long
        val denominator: Long

        init {
            var numerator = numerator
            var denominator = denominator
            if (denominator < 0) {
                numerator = -numerator
                denominator = -denominator
            }
            this.numerator = numerator
            this.denominator = denominator
        }

        companion object {
            @Contract("_, _ -> new")
            fun of(numerator: Long, denominator: Long): Fraction {
                return Fraction(numerator, denominator, numerator.toDouble() / denominator)
            }

            @Contract("_, _ -> new")
            fun of(numerator: Int, denominator: Int): Fraction {
                return simply(Fraction(numerator.toLong(), denominator.toLong(), numerator.toDouble() / denominator))
            }

            @Contract("_ -> new")
            fun of(decimalNumber: Double): Fraction {
                return toFraction(decimalNumber)
            }

            @Contract(value = "_ -> new", pure = true)
            private fun simply(fraction: Fraction): Fraction {
                val numerator = fraction.numerator
                val denominator = fraction.denominator
                var hcf: Long = 0

                var i: Long = 1
                while (i <= numerator || i <= denominator) {
                    if (numerator % i == 0L && denominator % i == 0L) hcf = i
                    i++
                }

                return Fraction(numerator / hcf, denominator / hcf, fraction.decimalNumber)
            }
        }
    }

    private fun String.removePlusOnStart() : String {
        var equationString = this
        if (equationString.startsWith("+")){
            equationString = equationString.replaceFirst("+","")
        } else if (equationString.contains("{+")){
            equationString = equationString.replace("{+","{")
        }
        return equationString
    }
    /*private fun Array<Term>.hcf() : Term {
        var hcfCoefficient = if (this[0].coefficient > 0) this[0].coefficient else -this[0].coefficient
        for (i in 1 until this.size) {
            var hcf = hcfCoefficient
            var hcf2 = if (this[i].coefficient > 0) this[i].coefficient else -this[i].coefficient
            while (hcf != hcf2) {
                if (hcf > hcf2) hcf -= hcf2
                else hcf2 -= hcf
            }
            hcfCoefficient = hcf
        }

        var hcf = this[0].variables.toMutableMap()
        val negative = mutableMapOf<Char, Double>()
        this[0].variables.forEach{
            if(it.value < 0){
                negative[it.key] = it.value
            }
        }
        for(i in 1 until this.size){
            val temp = mutableMapOf<Char, Double>()
            this[i].variables.forEach{
                val variable = it.key
                val exponent = it.value

                val index = hcf.keys.indexOf(variable)

                if (index != -1){
                    val value = hcf.values.toList()[index]
                    if(value <= exponent) temp[variable] = value
                    else temp[variable] = exponent
                } else if (exponent < 0) {
                    negative[variable] = exponent
                }
            }
            hcf = temp
        }
        hcf.putAll(negative)

        return Term(hcfCoefficient,hcf.toMap())
    }
    private fun Array<Term>.lcm() : Term {
        var lcmCoefficient = if (this[0].coefficient > 0) this[0].coefficient else -this[0].coefficient
        for (i in 1 until this.size){
            var hcf = lcmCoefficient
            var hcf2 = if (this[i].coefficient > 0) this[i].coefficient else -this[i].coefficient
            val n1 = hcf
            val n2 = hcf2
            while (hcf != hcf2) {
                if (hcf > hcf2)
                    hcf -= hcf2
                else
                    hcf2 -= hcf
            }
            lcmCoefficient = (n1 * n2)/hcf
        }

        var lcm = mutableMapOf<Char, Double>()
        this[0].variables.forEach{
            if(it.value > 0){
                lcm[it.key] = it.value
            }
        }
        for(i in 1 until this.size) {
            val temp = mutableMapOf<Char, Double>()
            this[i].variables.forEach{
                val variable = it.key
                val exponent = it.value

                val index = lcm.keys.indexOf(variable)

                if (index != -1){
                    val value = lcm.values.toList()[index]
                    if(exponent >= value && exponent > 0) temp[variable] = exponent
                    else if(value > 0) temp[variable] = value
                } else if(exponent > 0) {
                    temp[variable] = exponent
                }
            }
            lcm.forEach{
                if(!temp.keys.contains(it.key)){
                    temp[it.key] = it.value
                }
            }

            lcm = temp
        }

        return Term(lcmCoefficient,lcm)
    }*/
}
