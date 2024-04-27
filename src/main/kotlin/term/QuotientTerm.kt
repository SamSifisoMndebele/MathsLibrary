package com.ssmnd.term

import com.ssmnd.variable.Variable

class QuotientTerm : Term {

    /*override var coefficient: Double
    private val numTerms: TreeSet<Term>
    private val denTerms: TreeSet<Term>
    constructor(coefficient: Double, numTerms: Set<Term>, denTerms: Set<Term>) {
        this.coefficient = coefficient
        for (term in numTerms) this.coefficient *= term.coefficient
        for (term in denTerms) this.coefficient /= term.coefficient
        this.numTerms = TreeSet(numTerms.map { term -> term.also { it.coefficient = 1.0 } })
        this.denTerms = TreeSet(denTerms.map { term -> term.also { it.coefficient = 1.0 } })
    }
    constructor(coefficient: Double, numTerm: Term, denTerm: Term) : this(coefficient, setOf(numTerm), setOf(denTerm))
    constructor(numTerm: Term, denTerm: Term) : this(1.0, setOf(numTerm), setOf(denTerm))


    override fun toString(): String {
        if (isZero) return "0"
        if (isNaN()) return "NaN"
        if (isInfinite()) return if (isPositive()) "+\\infty" else "-\\infty"

        return """\frac{${numTerms.joinToString("")}}{${denTerms.joinToString("")}}"""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuotientTerm

        if (coefficient != other.coefficient) return false
        if (numTerms != other.numTerms) return false
        if (denTerms != other.denTerms) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coefficient.hashCode()
        result = 31 * result + numTerms.hashCode()
        result = 31 * result + denTerms.hashCode()
        return result
    }*/
    override var coefficient: Double
        get() = TODO("Not yet implemented")
        set(value) {}
    override val variables: Set<Variable>
        get() = TODO("Not yet implemented")

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override val comparableString: String
        get() = TODO("Not yet implemented")
    override val isNumber: Boolean
        get() = TODO("Not yet implemented")

    override fun isLikeTerm(other: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun plus(term: Term): Term {
        TODO("Not yet implemented")
    }

    override fun times(number: Number): Term {
        TODO("Not yet implemented")
    }

    override fun times(term: Term): Term {
        TODO("Not yet implemented")
    }

    override fun div(number: Number): Term {
        TODO("Not yet implemented")
    }

    override fun div(term: Term): Term {
        TODO("Not yet implemented")
    }
}