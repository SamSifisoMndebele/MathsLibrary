package com.ssmnd.util

import com.ssmnd.variable.ExponentialVariable
import com.ssmnd.variable.PolynomialVariable

/**
 * The `Latex` class represents character strings. All latex literals, such as `"frac{a}{b}"`, are
 * implemented as instances of this class.
 */
class Latex : CharSequence {
    private val string: String
    override val length: Int get() = string.length
    constructor() {
        string = ""
    }
    constructor(latex: String) {
        this.string = latex
    }
    constructor(variable: ExponentialVariable) {
        string = when (variable.base) {
            0.0 -> "0"
            1.0 -> "1"
            else -> "{${variable.base}}^{${variable.exponent}}"
        }
    }
    constructor(variable: PolynomialVariable) {
        string = when (variable.exponent) {
            0.0 -> "1"
            1.0 -> "${variable.base}"
            else -> "{${variable.base}}^{${variable.exponent}}"
        }
    }

    /**
     * Returns a latex string obtained by concatenating this latex string with the latex string representation of the given [other] object.
     */
    operator fun plus(other: Any?): Latex {
        if (this === other) return Latex(string+string)
        return when (other) {
            is Latex -> Latex(string + other.string)
            is String -> Latex(string+other)
            else -> Latex(string+other.toString())
        }
    }

    override fun toString(): String {
        return "$$string$"
    }

    override fun get(index: Int): Char {
        return string[index]
    }
    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
        return string.subSequence(startIndex, endIndex)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Latex

        return string == other.string
    }

    override fun hashCode(): Int {
        return string.hashCode()
    }

    companion object {
        val ZERO = Latex("0")
        val ONE = Latex("1")
    }
}