package com.ssmnd.variable

import com.ssmnd.term.ExponentialTerm
import com.ssmnd.term.Term
import com.ssmnd.util.Latex
import kotlin.math.ln
import kotlin.math.pow

/**
 * The `Exponential Variable` class represents a mathematical variable with a character base and an exponent.
 * All exponential variables, such as `2^n`, are
 * implemented as instances of this class.
 */
class ExponentialVariable(override var base: Double, override var exponent: Char) : Variable {
    constructor(base: Number, exponent: Char) : this(base.toDouble(), exponent)

    override fun toString(): String {
        if (base == 0.0) return "0"
        if (base == 1.0) return "1"
        return "{${base}^{$exponent}"
    }

    override fun toLatex(): Latex {
        return Latex(this)
    }

    override fun value(x: Number): Double {
        return base.pow(x.toDouble())
    }

    @Deprecated("This method may throw runtime errors.", ReplaceWith("set(base.toDouble(), value.toChar())"))
    override fun set(base: Any, value: Any) {
        set(base.toString().toDouble(), value.toString()[0])
    }

    override fun pow(n: Double): Variable {
        return ExponentialVariable(base.pow(n), exponent)
    }

    override fun derivative(n: Int): Term {
        return ExponentialTerm(ln(base).pow(n), base, exponent)
    }

    override fun div(variable: Variable): Variable {
        //TODO("Not yet implemented")
        if (variable !is ExponentialVariable || exponent != variable.exponent) throw IllegalArgumentException("$variable is not a like variable if $this")
        return ExponentialVariable(base / variable.base, exponent)
    }

    override fun times(variable: Variable): Variable {
        //TODO("Not yet implemented")
        if (variable !is ExponentialVariable || exponent != variable.exponent) throw IllegalArgumentException("$variable is not a like variable if $this")
        return ExponentialVariable(base * variable.base, exponent)
    }

    /**
     * Set the variable base and exponent.
     */
    operator fun set(base: Double, value: Char) {
        this.base = base
        this.exponent = value
    }

}