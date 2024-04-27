package com.ssmnd.term

import com.ssmnd.util.Latex
import com.ssmnd.variable.PolynomialVariable
import kotlin.math.pow

class PolynomialTerm : Term {
    override var coefficient: Double
    override val variables: Set<PolynomialVariable>

    constructor(coefficient: Double, variables: Set<PolynomialVariable>) {
        this.coefficient = coefficient
        this.variables = variables
        /*var coeff = coefficient
        variables.forEach {
            if (it.base.isLetter() && it.exponent != 0.0) {
                //this.variables[variable] = exponent.toDouble()
            } else if (variable.isDigit()) {
                //coeff *= variable.toString().toDouble().pow(exponent.toDouble())
            }
        }
        if (coeff == 0.0) this.variables.clear()
        this.coefficient = coeff*/
    }

    constructor(number: Number) : this(number.toDouble(), setOf())
    constructor(variable: Char) : this(1.0, setOf(PolynomialVariable(variable)))
    constructor(coefficient: Number, variable: Char) : this(
        coefficient.toDouble(), setOf(PolynomialVariable(variable)))
    constructor(coefficient: Number, variable: Char, exponent: Number) : this(
        coefficient.toDouble(), setOf(PolynomialVariable(variable, exponent)))
    constructor(variables: Set<PolynomialVariable>) : this(1.0, variables)

    companion object {
        val Any.instanceOfTerm: Boolean
            get() = PolynomialTerm::class.java.isInstance(this)

        val NEGATIVE_ONE = PolynomialTerm(-1.0, setOf())
        val ZERO = PolynomialTerm(0)
        val ONE = PolynomialTerm(1)
        val POSITIVE_INFINITY = PolynomialTerm(Double.POSITIVE_INFINITY)
        val NEGATIVE_INFINITY = PolynomialTerm(Double.NEGATIVE_INFINITY)
        val NaN = PolynomialTerm(Double.NaN)
    }

    override fun toString(): String {
        var string = ""
        variables.forEach { 
            string += "${it.base}^${it.exponent}"
        }
        return string
    }

    override val comparableString: String
        get() = variables.joinToString("") { key ->
            key.toString()// + variables.getOrDefault(key, 0.0)
        }

    override fun toLatex(): Latex {
        if (isZero) return Latex.ZERO
        if (isNaN()) return Latex.NaN
        if (isInfinite()) return if (isPositive()) Latex.POSITIVE_INFINITY else Latex.NEGATIVE_INFINITY
        val string = StringBuilder()
        if (coefficient < 0) {
            if (coefficient == -1.0 && variables.isEmpty()) return Latex.NEGATIVE_ONE
            if (coefficient == -1.0) string.append('-')
            else string.append(Latex(coefficient))
        } else if (coefficient > 0) {
            if (coefficient == 1.0 && variables.isEmpty()) return Latex.ONE
            string.append('+')
            if (coefficient != 1.0) string.append(Latex(coefficient))
        }
        return Latex("$string${variables}")
    }

    override fun value(vararg variables: Pair<Char, Number>): Double {
        var value = coefficient
        val varsMap = mapOf(*variables).mapValues { it.value.toDouble() }
        this.variables.forEach { variable ->
            value *= varsMap.getOrDefault(variable.base, 0.0).pow(variable.exponent)
        }
        return value
    }

    override val isNumber: Boolean
        get() = TODO("Not yet implemented")

    override fun isLikeTerm(other: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun partialDerivative(x: Char, n: Int): Term {
        TODO("Not yet implemented")
    }


    override fun div(term: Term): Term {
        //TODO("Not yet implemented")
        return PolynomialTerm(this.coefficient / term.coefficient, this.variables)
    }

    override fun times(term: Term): Term {
        //TODO("Not yet implemented")
        return PolynomialTerm(this.coefficient * term.coefficient, this.variables)
    }

    override fun div(number: Number): Term {
        TODO("Not yet implemented")
    }

    override fun minus(term: Term): Term {
        if (term !is PolynomialTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return PolynomialTerm(this.coefficient - term.coefficient, this.variables)
    }

    override fun times(number: Number): Term {
        TODO("Not yet implemented")
    }

    override fun plus(term: Term): Term {
        if (term !is PolynomialTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return PolynomialTerm(this.coefficient + term.coefficient, this.variables)
    }

}