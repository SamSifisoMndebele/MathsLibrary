package com.ssmnd.terms

import com.ssmnd.util.Util.plus
import com.ssmnd.util.Util.minus
import com.ssmnd.util.Util.times
import com.ssmnd.util.Util.div

class ExpTerm : Term {
    override val coefficient: Number
    val variables: Map<Number, Char>
    constructor(coefficient: Number, variables: Map<Number, Char>) {
        this.coefficient = coefficient
        this.variables = if (coefficient != 0.0) variables else mapOf()
    }
    constructor(number: Number) : this(number, mapOf())
    constructor(coefficient: Number, base: Number, exponent: Char) : this(coefficient, mapOf(base to exponent))
    constructor(variables: Map<Number, Char>) : this(1.0, variables)
    companion object {
        val ZERO = ExpTerm(0)
        val ONE = ExpTerm(1)
        fun Map<Number, Char>.string() : String {
            var string = ""
            this.forEach { (char, exp) -> string += "$char^$exp" }
            return string
        }
    }
    override fun toString(): String {
        return coefficient.toString() + variables.string()
    }


    override fun plus(term: Term): Term {
        if (term !is ExpTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return ExpTerm(this.coefficient + term.coefficient, this.variables)
    }

    override fun minus(term: Term): Term {
        if (term !is ExpTerm || this.variables != term.variables)
            throw ArithmeticException("$this is not a like term of $term")

        return ExpTerm(this.coefficient - term.coefficient, this.variables)
    }

    override fun times(term: Term): Term {
        //TODO("Not yet implemented")
        return ExpTerm(this.coefficient * term.coefficient, this.variables)
    }

    override fun div(term: Term): Term {
        //TODO("Not yet implemented")
        return ExpTerm(this.coefficient / term.coefficient, this.variables)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExpTerm

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