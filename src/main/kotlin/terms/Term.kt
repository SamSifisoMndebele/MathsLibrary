package com.ssmnd.terms

interface Term {
    val coefficient: Double
    override fun toString(): String

    fun value(vararg vars: Pair<Char, Number>): Double
    fun derivative(n: Int): Term

    @Throws(ArithmeticException::class)
    operator fun plus(term: Term): Term

    @Throws(ArithmeticException::class)
    operator fun minus(term: Term): Term
    operator fun times(term: Term): Term
    operator fun div(term: Term): Term

//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}