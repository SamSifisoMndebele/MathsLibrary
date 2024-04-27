package com.ssmnd.term

import com.ssmnd.variable.Variable

class ProductTerm : Term {
    /*override var coefficient: Double
    private val terms : TreeSet<Term>
    constructor(coefficient: Double, terms: Set<Term>) {
        this.coefficient = coefficient
        for (term in terms) this.coefficient *= term.coefficient
        this.terms = TreeSet(terms.map { term -> term.also { it.coefficient = 1.0 } })
    }
    constructor(coefficient: Double, term1: Term, term2: Term) : this(coefficient, setOf(term1, term2))
    constructor(term1: Term, term2: Term) : this(1.0, setOf(term1, term2))

    override val isOne: Boolean
        get() = terms.all { it.isOne }
    override val isZero: Boolean
        get() = terms.any { it.isZero }

    override fun toString(): String {
        if (isZero) return "0"
        if (isNaN()) return "NaN"
        if (isInfinite()) return if (isPositive()) "+\\infty" else "-\\infty"

        return terms.joinToString("")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductTerm

        if (coefficient != other.coefficient) return false
        if (terms != other.terms) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coefficient.hashCode()
        result = 31 * result + terms.hashCode()
        return result
    }

    override val comparableString: String
        get() = "" //TODO("Not yet implemented")

    override fun value(vararg variables: Pair<Char, Number>): Double {
        var value = coefficient
        for (term in terms) {
            value *= term.value(*variables)
        }
        return value
    }

    override val isNumber: Boolean
        get() = terms.all { it.isNumber }

    override fun isLikeTerm(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false
        other as ProductTerm

        return terms.containsAll(other.terms) && other.terms.containsAll(terms)
    }

    fun plusLike(term: Term): Term {
        return if (isLikeTerm(term)) {
            val coefficient = coefficient + term.coefficient
            if (coefficient == 0.0) PolynomialTerm.ZERO
            else ProductTerm(coefficient, terms)
        } else {
            throw ArithmeticException("Terms {$this} and {$term} are not like terms.")
        }
    }

    override fun unaryMinus(): Term {
        return ProductTerm(-coefficient, terms)
    }

    override fun plus(number: Number): Expression {
        val double = number.toDouble()
        if (double.isNaN() || isNaN()) return ExpressionImpl.NaN
        if (double.isInfinite() || isInfinite())
            return if (double >= 0 && isPositive() || double < 0 && isNegative()) ExpressionImpl.POSITIVE_INFINITY
            else ExpressionImpl.NEGATIVE_INFINITY
        if (double == 0.0) return ExpressionImpl(this)
        if (isZero) return ExpressionImpl(number)

        //return ExpressionImpl(setOf(this, PolynomialTerm(number)))
        TODO()
    }

    override fun plus(term: Term): Expression {
        if (term.isNaN() || isNaN()) return ExpressionImpl.NaN
        if (term.isInfinite() || isInfinite())
            return if (term.isPositive() && isPositive() || term.isNegative() && isNegative()) ExpressionImpl.POSITIVE_INFINITY
            else ExpressionImpl.NEGATIVE_INFINITY
        if (term.isZero) return ExpressionImpl(this)
        if (this.isZero) return ExpressionImpl(term)

        return try {
            ExpressionImpl(plusLike(term))
        } catch (_: Exception) {
            //ExpressionImpl(setOf(this, term))
            TODO()
        }
    }

    override fun plus(expression: Expression): Expression {
        if (expression.isNaN() || isNaN()) return ExpressionImpl.NaN
        if (expression.isInfinite() || isInfinite())
            return if (expression.isPositive() && isPositive() || expression.isNegative() && isNegative()) ExpressionImpl.POSITIVE_INFINITY
            else ExpressionImpl.NEGATIVE_INFINITY
        if (expression.isZero) return ExpressionImpl(this)
        if (this.isZero) return expression

        val sumTerms = mutableSetOf<Term>()
        val iterator = expression.terms.iterator()
        var hasLikeTerm = false
        while (iterator.hasNext()) {
            val term = iterator.next()
            try {
                sumTerms.add(plusLike(term))
                hasLikeTerm = true
                break
            } catch (_:Exception) {
                sumTerms.add(term)
            }
        }
        if (hasLikeTerm) {
            iterator.forEachRemaining { term -> sumTerms.add(term) }
        } else sumTerms.add(this)

        //return ExpressionImpl(sumTerms)
        TODO()
    }

    override fun times(number: Number): Term {
        val coefficient = number.toDouble() * coefficient
        if (coefficient.isNaN()) return PolynomialTerm.NaN
        if (coefficient.isInfinite()) return if (coefficient >= 0) PolynomialTerm.POSITIVE_INFINITY else PolynomialTerm.NEGATIVE_INFINITY
        if (coefficient == 0.0) return PolynomialTerm.ZERO

        return ProductTerm(coefficient, terms)
    }

    private fun getTypeTerm(term: Term) : Term? {
        terms.forEach { term1->
            if (term1.isLikeTerm(term)) return term1
        }
        return null
    }

    override fun times(term: Term): Term {
        val coefficient = term.coefficient * coefficient
        if (coefficient.isNaN()) return PolynomialTerm.NaN
        if (coefficient.isInfinite()) return if (coefficient >= 0) PolynomialTerm.POSITIVE_INFINITY else PolynomialTerm.NEGATIVE_INFINITY
        if (coefficient == 0.0) return PolynomialTerm.ZERO

        term.coefficient = 1.0
        val sameTypeTerm = getTypeTerm(term)
        return if (sameTypeTerm == null) ProductTerm(coefficient, terms.apply { add(term) })
        else ProductTerm(coefficient, terms.apply { remove(sameTypeTerm); add(sameTypeTerm * term) })
    }

    override fun times(expression: Expression): Expression {
        if (expression.isNaN() || isNaN()) return ExpressionImpl.NaN
        if (expression.isInfinite() || isInfinite())
            return if (expression.isPositive() && isPositive() || expression.isNegative() && isNegative()) ExpressionImpl.POSITIVE_INFINITY
            else ExpressionImpl.NEGATIVE_INFINITY
        if (expression.isZero || isZero) return ExpressionImpl.ZERO

        var product : Expression = ExpressionImpl.ZERO
        expression.terms.forEach {term ->
            product += times(term)
        }
        return product
    }

    override fun div(number: Number): Term {
        val coefficient = number.toDouble() / coefficient
        if (coefficient.isNaN()) return PolynomialTerm.NaN
        if (coefficient.isInfinite()) return if (coefficient >= 0) PolynomialTerm.POSITIVE_INFINITY else PolynomialTerm.NEGATIVE_INFINITY
        if (coefficient == 0.0) return PolynomialTerm.ZERO

        return ProductTerm(coefficient, terms)
    }*/

    /*fun divideBy(term: PolynomialTerm): PolynomialTerm {
        val coefficient: Double = coefficient / term.coefficient
        return if (coefficient == 0.0 || this.variables.isEmpty() && term.variables.isEmpty() ||
            java.lang.Double.isInfinite(coefficient) || java.lang.Double.isNaN(coefficient)
        ) {
            PolynomialTerm(coefficient)
        } else if (term.variables.isEmpty()) {
            PolynomialTerm(coefficient, this.variables)
        } else {
            val resultVars: MutableMap<Char, Double> = LinkedHashMap<Char, Double>(variables)
            val commonVars: MutableSet<Char> = LinkedHashSet<Char>(variables.keys)
            val termVars: MutableSet<Char> = LinkedHashSet<Char>(term.variables.keys)
            commonVars.retainAll(termVars)
            termVars.removeAll(commonVars)
            for (variable in commonVars) {
                val expA: Double = variables.get(variable)!!
                val expB: Double = term.variables.get(variable)!!
                val newExponent = expA!! - expB!!
                if (newExponent != 0.0) resultVars[variable] = newExponent
            }
            for (variable in termVars) {
                val exponent: Double = term.variables.get(variable)!!
                resultVars[variable] = exponent!! * -1
            }
            PolynomialTerm(coefficient, resultVars)
        }
    }*/
    /*override fun div(term: Term): Term {
        val coefficient = term.coefficient / coefficient
        if (coefficient.isNaN()) return PolynomialTerm.NaN
        if (coefficient.isInfinite()) return if (coefficient >= 0) PolynomialTerm.POSITIVE_INFINITY else PolynomialTerm.NEGATIVE_INFINITY
        if (coefficient == 0.0) return PolynomialTerm.ZERO

        term.coefficient = 1.0
        val sameTypeTerm = getTypeTerm(term)
        return if (sameTypeTerm == null) QuotientTerm(coefficient, terms, setOf(term))
        else ProductTerm(coefficient, terms.apply { remove(sameTypeTerm); add(sameTypeTerm / term) })
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