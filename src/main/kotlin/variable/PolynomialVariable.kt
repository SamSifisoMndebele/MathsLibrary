package com.ssmnd.variable

import com.ssmnd.util.Latex
import kotlin.math.pow

/**
 * The `Polynomial Variable` class represents a mathematical variable with a character base and an exponent.
 * All polynomial variables, such as `x, y^2`, are
 * implemented as instances of this class.
 */
class PolynomialVariable(override var base: Char, override var exponent: Double) : Variable<Char, Double> {
    companion object {
        fun polynomialVariable(base: Char) = PolynomialVariable(base, 1.0)
        fun polynomialVariable(base: Char, exponent: Number) = PolynomialVariable(base, exponent.toDouble())
    }

    override fun toString(): String {
        if (exponent == 0.0) return "1"
        if (exponent == 1.0) return "$base"
        return "$base^$exponent"
    }

    override fun toLatex(): Latex {
        return Latex(this)
    }

    override fun value(value: Number): Double {
        //TODO("Not yet implemented")
        return value.toDouble().pow(exponent)
    }

    override fun pow(n: Double): Variable<Char, Double> {
        //TODO("Not yet implemented")
        return PolynomialVariable(base, exponent * n)
    }

    override fun derivative(n: Int): Variable<Char, Double> {
        TODO("Not yet implemented")
    }

    override fun div(variable: Variable<Char, Double>): Variable<Char, Double> {
        //TODO("Not yet implemented")
        if (variable !is PolynomialVariable || base != variable.base) throw IllegalArgumentException("$variable is not a like variable if $this")
        return PolynomialVariable(base, exponent - variable.exponent)
    }

    override fun times(variable: Variable<Char, Double>): Variable<Char, Double> {
        //TODO("Not yet implemented")
        if (variable !is PolynomialVariable || base != variable.base) throw IllegalArgumentException("$variable is not a like variable if $this")
        return PolynomialVariable(base, exponent + variable.exponent)
    }

    override fun set(base: Char, value: Double) {
        this.base = base
        this.exponent = value
    }

    override fun compareTo(other: Variable<Char, Double>): Int {
        TODO("Not yet implemented")
    }


}