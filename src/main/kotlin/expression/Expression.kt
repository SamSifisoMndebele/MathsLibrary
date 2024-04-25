package com.ssmnd.expression

import com.ssmnd.terms.Term

interface Expression {
    val terms: Array<Term>
    override fun toString(): String

    fun value(vararg vars: Pair<Char, Number>): Double
    fun derivative(n: Int): Expression
    val firstDerivative: Expression get() = derivative(1)
    val secondDerivative: Expression get() = derivative(2)

    operator fun plus(expression: Expression): Expression
    operator fun minus(expression: Expression): Expression
    operator fun times(expression: Expression): Expression
    operator fun div(expression: Expression): Expression

//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}