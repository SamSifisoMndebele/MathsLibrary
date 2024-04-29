package com.ssmnd.utils

import com.ssmnd.expressions.term.Term
import com.ssmnd.expressions.variable.Variable

/**
 * The `Latex` class represents character strings. All latex literals, such as `"frac{a}{b}"`, are
 * implemented as instances of this class.
 */
class Latex : CharSequence {
    private val string: String
    override val length: Int get() = string.length
    private constructor() {
        string = ""
    }
    constructor(latex: Latex) {
        this.string = latex.string
    }
    constructor(latexString: String) {
        this.string = latexString.removePrefix("$").removeSuffix("$")
    }
    constructor(number: Number, parenthesis: Boolean = false) {
        var fraction = number.toFraction()
        val isNegative = number.toDouble() < 0
        if (isNegative) fraction = fraction.negate
        val latex = StringBuilder()
        if (isNegative) latex.append('-')
        if (fraction.denominator == 1L) {
            latex.append(fraction.numerator)
        } else {
            if (parenthesis) latex.append('(')
            latex.append("frac{")
                .append(fraction.numerator)
                .append("}{")
                .append(fraction.denominator)
                .append("}")
            if (parenthesis) latex.append(')')
        }
        string = latex.toString()
    }
    constructor(term: Term) {
        var latex = Latex()
        if (term.coefficient == 0.0) {
            string = "0"
            return
        } else if (term.coefficient > 0) {
            if (term.coefficient == 1.0 && term.variables.isEmpty()) {
                string = "1"
                return
            }
            if (term.coefficient != 1.0) latex += Latex(term.coefficient)
        } else {
            if (term.coefficient == -1.0 && term.variables.isEmpty()) {
                string = "-1"
                return
            } else if (term.coefficient == -1.0) {
                latex += '-'
            }
            if (term.coefficient != -1.0) latex += Latex(term.coefficient)
        }
        term.variables.forEach {
            latex += it.toLatex()
        }
        string = latex.string
    }
    constructor(variable: Variable) {
        string = when {
            variable.baseDouble == 0.0 && variable.exponentDouble == 0.0 -> NAN_STR
            variable.baseDouble == 0.0 -> "0"
            variable.exponentDouble == 0.0 || variable.baseDouble == 1.0 -> "1"
            variable.exponentDouble == 1.0 -> if (variable.base is Char) variable.base.toString() else Latex(variable.base.toString().toDouble()).string
            else -> "{${
                if (variable.base is Char) variable.base.toString() else Latex(variable.base.toString().toDouble()).string
            }}^{${
                if (variable.exponent is Char) variable.exponent.toString() else Latex(variable.exponent.toString().toDouble()).string
            }}"
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
            is Char -> Latex(string+other)
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
        private const val NAN_STR = "NaN"
        private const val P_INF_STR = "+\\infty"
        private const val N_INF_STR = "-\\infty"
        val ZERO = Latex("0")
        val ONE = Latex("1")
        val NEGATIVE_ONE = Latex("-1")
        val NaN = Latex(NAN_STR)
        val POSITIVE_INFINITY = Latex(P_INF_STR)
        val NEGATIVE_INFINITY = Latex(N_INF_STR)
    }
}