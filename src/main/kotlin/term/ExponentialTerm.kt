package com.ssmnd.term

import com.ssmnd.util.Latex
import com.ssmnd.variable.ExponentialVariable
import com.ssmnd.variable.Variable

class ExponentialTerm(override var coefficient: Double, override val variables: Set<Variable>) : Term {
    constructor(number: Number) : this(number.toDouble(), setOf())
    constructor(coefficient: Number, base: Number, exponent: Char) : this(
        coefficient.toDouble(), setOf(ExponentialVariable(base.toDouble(), exponent)))
    constructor(variables: Set<Variable>) : this(1.0, variables)

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override val comparableString: String
        get() = TODO("Not yet implemented")

    override fun toLatex(): Latex {
        TODO("Not yet implemented")
    }

    override fun value(vararg variables: Pair<Char, Number>): Double {
        TODO("Not yet implemented")
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
        return ExponentialTerm(this.coefficient / term.coefficient, this.variables)
    }

    override fun times(term: Term): Term {
        //TODO("Not yet implemented")
        return ExponentialTerm(this.coefficient * term.coefficient, this.variables)
    }

    override fun div(number: Number): Term {
        TODO("Not yet implemented")
    }

    override fun minus(term: Term): Term {
        if (term !is ExponentialTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return ExponentialTerm(this.coefficient - term.coefficient, this.variables)
    }

    override fun times(number: Number): Term {
        TODO("Not yet implemented")
    }

    override fun plus(term: Term): Term {
        if (term !is ExponentialTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return ExponentialTerm(this.coefficient + term.coefficient, this.variables)
    }

}