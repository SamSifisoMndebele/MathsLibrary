package com.ssmnd.variable

import com.ssmnd.term.PolynomialTerm
import com.ssmnd.term.Term
import com.ssmnd.util.Latex
import kotlin.math.pow

/**
 * The `Polynomial Variable` class represents a mathematical variable with a character base and an exponent.
 * All polynomial variables, such as `x, y^2`, are
 * implemented as instances of this class.
 */
class PolynomialVariable : Variable {
    override var base: Char
        private set
    override var exponent: Double
        private set
    constructor(base: Char, exponent: Double) {
        this.base = base
        this.exponent = exponent
    }

    constructor(base: Char, exponent: Number) : this(base, exponent.toDouble())
    constructor(base: Char) : this(base, 1.0)

    override fun toString(): String {
        if (exponent == 0.0) return "1"
        if (exponent == 1.0) return "$base"
        return "$base^$exponent"
    }

    override fun toLatex(): Latex {
        return Latex(this)
    }

    override fun value(x: Number): Double {
        return x.toDouble().pow(exponent)
    }

    @Deprecated("This method may throw runtime errors.", ReplaceWith("set(base.toChar(), value.toDouble())"))
    override fun set(base: Any, value: Any) {
        set(base.toString()[0], value.toString().toDouble())
    }

    override fun pow(n: Double): Variable {
        return PolynomialVariable(base, exponent * n)
    }

    override fun derivative(n: Int): Term {
        var coefficient = 1.0
        for (i in 0 until n) {
            coefficient *= exponent - i
            if (coefficient == 0.0) return PolynomialTerm(0.0, base, 0.0)
        }
        return PolynomialTerm(coefficient, base, exponent-n)
    }

    override fun div(variable: Variable): Variable {
        //TODO("Not yet implemented")
        if (variable !is PolynomialVariable || base != variable.base) throw IllegalArgumentException("$variable is not a like variable if $this")
        return PolynomialVariable(base, exponent - variable.exponent)
    }

    override fun times(variable: Variable): Variable {
        //TODO("Not yet implemented")
        if (variable !is PolynomialVariable || base != variable.base) throw IllegalArgumentException("$variable is not a like variable if $this")
        return PolynomialVariable(base, exponent + variable.exponent)
    }

    /**
     * Set the variable base and exponent.
     */
    operator fun set(base: Char, value: Double) {
        this.base = base
        this.exponent = value
    }
}