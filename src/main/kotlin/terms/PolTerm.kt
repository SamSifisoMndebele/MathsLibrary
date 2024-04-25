package com.ssmnd.terms

import com.ssmnd.util.Util.plus
import com.ssmnd.util.Util.minus
import com.ssmnd.util.Util.times
import com.ssmnd.util.Util.div

class PolTerm : Term {
    override val coefficient: Number
    val variables: Map<Char, Number>
    constructor(coefficient: Number, variables: Map<Char, Number>) {
        this.coefficient = coefficient
        this.variables = if (coefficient != 0.0) variables else mapOf()
    }
    constructor(number: Number) : this(number, mapOf())
    constructor(coefficient: Number, variable: Char) : this(coefficient, mapOf(variable to 1.0))
    constructor(coefficient: Number, variable: Char, exponent: Number) : this(coefficient, mapOf(variable to exponent))
    constructor(variables: Map<Char, Number>) : this(1.0, variables)
    companion object {
        val ZERO = PolTerm(0)
        val ONE = PolTerm(1)
        fun Map<Char, Number>.string() : String {
            var string = ""
            this.forEach { (char, exp) -> string += "$char^$exp" }
            return string
        }
    }
    override fun toString(): String {
        return coefficient.toString() + variables.string()
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PolTerm

        if (coefficient != other.coefficient) return false
        if (variables != other.variables) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coefficient.hashCode()
        result = 31 * result + variables.hashCode()
        return result
    }
}