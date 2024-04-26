package com.ssmnd.term

import com.ssmnd.util.Latex
import com.ssmnd.variable.ExponentialVariable
import com.ssmnd.variable.ExponentialVariable.Companion.exponentialVariable

class ExponentialTerm(override val coefficient: Double, override val variables: Set<ExponentialVariable>) : Term<ExponentialVariable> {
    companion object {

        const val DEFAULT_VAR = 'x'
        fun exponentialTerm(number: Number) = ExponentialTerm(number.toDouble(), setOf())
        fun exponentialTerm(coefficient: Number, base: Number, exponent: Char) = ExponentialTerm(
            coefficient.toDouble(), setOf(exponentialVariable(base.toDouble(), exponent))
        )
        fun exponentialTerm(variables: Set<ExponentialVariable>) = ExponentialTerm(1.0, variables)
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun toLatex(): Latex {
        TODO("Not yet implemented")
    }

    override fun value(vararg vars: Pair<Char, Number>): Double {
        TODO("Not yet implemented")
    }

    override fun derivative(n: Int): Term<ExponentialVariable> {
        TODO("Not yet implemented")
    }

    override fun div(term: Term<ExponentialVariable>): Term<ExponentialVariable> {
        TODO("Not yet implemented")
    }

    override fun times(term: Term<ExponentialVariable>): Term<ExponentialVariable> {
        TODO("Not yet implemented")
    }

    override fun minus(term: Term<ExponentialVariable>): Term<ExponentialVariable> {
        TODO("Not yet implemented")
    }

    override fun plus(term: Term<ExponentialVariable>): Term<ExponentialVariable> {
        TODO("Not yet implemented")
    }

}