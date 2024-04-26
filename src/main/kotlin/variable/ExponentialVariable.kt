package com.ssmnd.variable

import com.ssmnd.util.Latex
import kotlin.math.pow

/**
 * The `Exponential Variable` class represents a mathematical variable with a character base and an exponent.
 * All exponential variables, such as `2^n`, are
 * implemented as instances of this class.
 */
class ExponentialVariable(override var base: Double, override var exponent: Char) : Variable<Double, Char> {
    companion object {
        fun exponentialVariable(base: Number, exponent: Char) = ExponentialVariable(base.toDouble(), exponent)
    }
    override fun compareTo(other: Variable<Double, Char>): Int {
        //TODO("Not yet implemented")
        return this.toString().compareTo(other.toString())
    }

    override fun toString(): String {
        if (base == 0.0) return "0"
        if (base == 1.0) return "1"
        return "{${base}^{$exponent}"
    }

    override fun toLatex(): Latex {
        return Latex(this)
    }

    override fun value(value: Number): Double {
        //TODO("Not yet implemented")
        return base.pow(value.toDouble())
    }

    override fun pow(n: Double): Variable<Double, Char> {
        TODO("Not yet implemented")
    }

    override fun derivative(n: Int): Variable<Double, Char> {
        TODO("Not yet implemented")
    }

    override fun div(variable: Variable<Double, Char>): Variable<Double, Char> {
        //TODO("Not yet implemented")
        if (variable !is ExponentialVariable || exponent != variable.exponent) throw IllegalArgumentException("$variable is not a like variable if $this")
        return ExponentialVariable(base / variable.base, exponent)
    }

    override fun times(variable: Variable<Double, Char>): Variable<Double, Char> {
        //TODO("Not yet implemented")
        if (variable !is ExponentialVariable || exponent != variable.exponent) throw IllegalArgumentException("$variable is not a like variable if $this")
        return ExponentialVariable(base * variable.base, exponent)
    }

    override fun set(base: Double, value: Char) {
        this.base = base
        this.exponent = value
    }

}