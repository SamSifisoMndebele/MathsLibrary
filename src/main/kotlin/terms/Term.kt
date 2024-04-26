package com.ssmnd.terms

import com.ssmnd.util.Latex

interface Term {
    val coefficient: Double

    /**
     * String representation of the term.
     * @return [Latex]
     */
    override fun toString(): String

    /**
     * Latex representation of the term.
     * @return [Latex]
     */
    fun toLatex(): Latex

    /**
     * Returns the value of the variable.
     * @param value The value of the variable, 0.0 by default.
     * @return [Double]: The value of the variable.
     */
    fun value(vararg vars: Pair<Char, Number>): Double
    fun derivative(n: Int): Term

    /**
     * Returns the positive of this value.
     * @return [Term]
     */
    operator fun unaryPlus(): Term = this
    @Throws(ArithmeticException::class)
    operator fun plus(term: Term): Term

    @Throws(ArithmeticException::class)
    operator fun minus(term: Term): Term
    operator fun times(term: Term): Term
    operator fun div(term: Term): Term

//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}