package com.ssmnd.term

import com.ssmnd.util.Latex
import com.ssmnd.variable.PolynomialVariable
import com.ssmnd.variable.PolynomialVariable.Companion.polynomialVariable
import kotlin.math.pow

class PolynomialTerm(override val coefficient: Double, override val variables: Set<PolynomialVariable>) : Term<PolynomialVariable> {
    companion object {

        const val DEFAULT_VAR = 'x'
        fun polynomialTerm(number: Number) = PolynomialTerm(number.toDouble(), setOf())
        fun polynomialTerm(variable: Char) = PolynomialTerm(1.0, setOf(polynomialVariable(variable)))
        fun polynomialTerm(coefficient: Number, variable: Char) = PolynomialTerm(coefficient.toDouble(), setOf(
            polynomialVariable(variable)
        ))
        fun polynomialTerm(coefficient: Number, variable: Char, exponent: Number) = PolynomialTerm(
            coefficient.toDouble(),
            setOf(polynomialVariable(variable, exponent))
        )
        fun polynomialTerm(variables: Set<PolynomialVariable>) = PolynomialTerm(1.0, variables)
    }
    override fun toString(): String {
        var string = ""
        variables.forEach { 
            string += "${it.base}^${it.exponent}"
        }
        return string
    }

    override fun toLatex(): Latex {
        var latex = Latex()
        variables.forEach {
            latex += "${it.base}^${it.exponent}"
        }
        return latex
    }

    override fun value(vararg vars: Pair<Char, Number>): Double {
        var value = coefficient
        val varsMap = mapOf(*vars).mapValues { it.value.toDouble() }
        variables.forEach { variable ->
            value *= varsMap.getOrDefault(variable.base, 0.0).pow(variable.exponent)
        }
        return value
    }

    override fun derivative(n: Int): Term<PolynomialVariable> {
        TODO("Not yet implemented")
    }

    override fun div(term: Term<PolynomialVariable>): Term<PolynomialVariable> {
        //TODO("Not yet implemented")
        return PolynomialTerm(this.coefficient / term.coefficient, this.variables)
    }

    override fun times(term: Term<PolynomialVariable>): Term<PolynomialVariable> {
        //TODO("Not yet implemented")
        return PolynomialTerm(this.coefficient * term.coefficient, this.variables)
    }

    override fun minus(term: Term<PolynomialVariable>): Term<PolynomialVariable> {
        if (term !is PolynomialTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return PolynomialTerm(this.coefficient - term.coefficient, this.variables)
    }

    override fun plus(term: Term<PolynomialVariable>): Term<PolynomialVariable> {
        if (term !is PolynomialTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return PolynomialTerm(this.coefficient + term.coefficient, this.variables)
    }

}