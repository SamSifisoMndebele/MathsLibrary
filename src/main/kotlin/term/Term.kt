package com.ssmnd.term

import com.ssmnd.util.Latex

interface Term<V> {
    val coefficient: Double
    val variables: Set<V>

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
    fun derivative(n: Int): Term<V>

    /**
     * Returns the positive of this value.
     * @return [Term<V>]
     */
    operator fun unaryPlus(): Term<V> = this
    @Throws(ArithmeticException::class)
    operator fun plus(term: Term<V>): Term<V>

    @Throws(ArithmeticException::class)
    operator fun minus(term: Term<V>): Term<V>
    operator fun times(term: Term<V>): Term<V>
    operator fun div(term: Term<V>): Term<V>

//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}