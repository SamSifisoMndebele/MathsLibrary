package com.ssmnd.terms

import com.ssmnd.util.Latex
import kotlin.math.pow

class PolTerm private constructor(override val coefficient: Double, variables: Map<Char, Double>) : Term {
    val variables: Map<Char, Double> = if (coefficient != 0.0) variables else mapOf()

    companion object {
        val ZERO : Term = PolTerm(0.0, mapOf())
        val ONE : Term = PolTerm(1.0, mapOf())
        fun Map<Char, Number>.string(): String {
            var string = ""
            this.forEach { (char, exp) -> string += "$char^$exp" }
            return string
        }

        fun polTermOf(number: Number) : Term = PolTerm(number.toDouble(), mapOf())
        fun polTermOf(variable: Char) : Term = PolTerm(1.0, mapOf(variable to 1.0))
        fun polTermOf(coefficient: Number, variable: Char) : Term = PolTerm(coefficient.toDouble(), mapOf(variable to 1.0))
        fun polTermOf(coefficient: Number, variable: Char, exponent: Number) : Term = PolTerm(
            coefficient.toDouble(),
            mapOf(variable to exponent.toDouble())
        )
        fun polTermOf(variables: Map<Char, Number>) : Term = PolTerm(1.0, variables.mapValues { it.value.toDouble() })
    }
    

    override fun toString(): String {
        return coefficient.toString() + variables.string()
    }

    override fun toLatex(): Latex {
        TODO("Not yet implemented")
    }

    override fun value(vararg vars: Pair<Char, Number>): Double {
        var value = coefficient
        val varsMap = mapOf(*vars).mapValues { it.value.toDouble() }
        variables.forEach { (base, exponent) ->
            value *= varsMap.getOrDefault(base, 0.0).pow(exponent)
        }
        return value
    }

    override fun derivative(n: Int): Term {
        TODO("Not yet implemented")
    }


    override fun plus(term: Term): Term {
        if (term !is PolTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return PolTerm(this.coefficient + term.coefficient, this.variables)
    }

    override fun minus(term: Term): Term {
        if (term !is PolTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return PolTerm(this.coefficient - term.coefficient, this.variables)
    }

    override fun times(term: Term): Term {
        //TODO("Not yet implemented")
        return PolTerm(this.coefficient * term.coefficient, this.variables)
    }

    override fun div(term: Term): Term {
        //TODO("Not yet implemented")
        return PolTerm(this.coefficient / term.coefficient, this.variables)
    }
}