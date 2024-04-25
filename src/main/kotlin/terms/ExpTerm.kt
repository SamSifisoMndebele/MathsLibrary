package com.ssmnd.terms

class ExpTerm(override val coefficient: Double, variables: Map<Double, Char>) : Term {
    val variables: Map<Double, Char> = if (coefficient != 0.0) variables.mapKeys { it.key } else mapOf()

    companion object {
        val ZERO : Term = ExpTerm(0.0, mapOf())
        val ONE : Term = ExpTerm(1.0, mapOf())
        fun Map<Double, Char>.string(): String {
            var string = ""
            this.forEach { (char, exp) -> string += "$char^$exp" }
            return string
        }

        fun expTermOf(number: Number) : Term = ExpTerm(number.toDouble(), mapOf())
        fun expTermOf(coefficient: Number, base: Number, exponent: Char) : Term = ExpTerm(
            coefficient.toDouble(),
            mapOf(base.toDouble() to exponent)
        )
        fun expTermOf(variables: Map<Number, Char>) : Term = ExpTerm(1.0, variables.mapKeys { it.key.toDouble() })
    }

    override fun toString(): String {
        return coefficient.toString() + variables.string()
    }

    override fun value(vararg vars: Pair<Char, Number>): Double {
        TODO("Not yet implemented")
    }

    override fun derivative(n: Int): Term {
        TODO("Not yet implemented")
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

}