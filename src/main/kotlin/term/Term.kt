package com.ssmnd.term

import com.ssmnd.expression.Expression
import com.ssmnd.util.Latex
import com.ssmnd.variable.Variable

interface Term : Comparable<Term>{
    /**
     * The coefficient of the term.
     */
    var coefficient: Double

    /**
     * The set of variables product in the term.
     */
    val variables: Set<Variable>

    /**
     * String representation of the term.
     * @return [String]
     */
    override fun toString(): String

    val comparableString : String
    override fun compareTo(other: Term): Int = other.comparableString.compareTo(comparableString)

    /**
     * Latex representation of the term.
     * @return [Latex]
     */
    fun toLatex(): Latex {
        if (isZero) return Latex.ZERO
        if (isNaN()) return Latex.NaN
        if (isInfinite()) return if (isPositive()) Latex.POSITIVE_INFINITY else Latex.NEGATIVE_INFINITY
        return Latex(coefficient) + variables.joinToString { it.toString() }
    }

    /**
     * Returns the value of the term.
     * @param variables variables values set, 0.0 by default.
     * @return [Double] value of the variable.
     */
    fun value(vararg variables: Pair<Char, Number>): Double {
        var value = coefficient
        val varsV = mapOf(*variables)
        this.variables.forEach { variable ->
            value *= variable.value(varsV[variable.base]?.toDouble() ?: 0.0)
        }
        return value
    }

    /**
     * Checks if the term is a Number without variables.
     * @return [Boolean]
     */
    val isNumber: Boolean

    /**
     * Checks if the term is equals to One.
     * @return [Boolean]
     */
    val isOne get() = isNumber && coefficient == 1.0

    /**
     * Checks if the term is equals to Zero.
     * @return [Boolean]
     */
    val isZero get() = isNumber && coefficient == 0.0

    /**
     * Checks if the term is Not a Number.
     * @param variables The pair of variables values, all variables values are 1.0 by default.
     * @return [Boolean]
     */
    fun isNaN(vararg variables: Pair<Char, Number>) = value(*variables).isNaN()

    /**
     * Checks if the term is Negative.
     * @param variables The pair of variables values, all variables values are 1.0 by default.
     * @return [Boolean]
     */
    fun isNegative(vararg variables: Pair<Char, Number>) = value(*variables) < 0.0

    /**
     * Checks if the term is Positive.
     * @param variables The pair of variables values, all variables values are 1.0 by default.
     * @return [Boolean]
     */
    fun isPositive(vararg variables: Pair<Char, Number>) = value(*variables) >= 0.0

    /**
     * Checks if the term is Finite.
     * @param variables The pair of variables values, all variables values are 1.0 by default.
     * @return [Boolean]
     */
    fun isFinite(vararg variables: Pair<Char, Number>) = value(*variables).isFinite()

    /**
     * Checks if the term is Infinite.
     * @param variables The pair of variables values, all variables values are 1.0 by default.
     * @return [Boolean]
     */
    fun isInfinite(vararg variables: Pair<Char, Number>) = value(*variables).isInfinite()


    /**
     * Checks if the term if a like term with other.
     * @param other The term to check if is like term with this term
     * @return [Boolean]
     */
    fun isLikeTerm(other: Any) : Boolean

    /**
     * Returns the partial derivative of this term.
     * @param x the variable to derive with respect to.
     * @param n the degree if the derivative. `e.g n = 1 for first derivative, n = 2 for second derivative.`
     * @return [Term]
     */
    fun partialDerivative(x: Char, n: Int): Term /*{
        var coefficient = this.coefficient
        var removeVar : Variable
        for (variable in variables) {
            removeVar = variable

        }


        //return PolynomialTerm(coefficient * exp, variables.also { it[base] = exp - 1 })
        TODO()
    }*/

    /**
     * Returns the derivative of this term.
     * @param /n the degree if the derivative. `e.g n = 1 for first derivative, n = 2 for second derivative.`
     * @return [Expression]
     */
    //fun derivative(n: Int): Expression

    /**
     * Returns the positive of this value.
     * @return [Term]
     */
    operator fun unaryPlus(): Term = this

    /**
     * Returns the negative of this value.
     * @return [Term]
     */
    operator fun unaryMinus(): Term = this.apply { coefficient = -coefficient }

    /**
     * Adds the term value to this value.
     * @param term A [Term]
     * @return [Expression]
     * @throws ArithmeticException if the term is not a like term
     */
    @Throws(ArithmeticException::class)
    operator fun plus(term: Term): Term

    /**
     * Subtracts the term value from this value.
     * @param term A [Term]
     * @return [Expression]
     * @throws ArithmeticException if the term is not a like term
     */
    @Throws(ArithmeticException::class)
    operator fun minus(term: Term): Term = this + -term

    /**
     * Multiplies this value by the number value.
     * @param number A [Number]
     * @return [Term]
     */
    operator fun times(number: Number): Term

    /**
     * Multiplies this value by the term value.
     * @param term A [Term]
     * @return [Term]
     */
    operator fun times(term: Term): Term

    /**
     * Divides this value by the number value.
     * @param number A [Number]
     * @return [Expression]
     */
    operator fun div(number: Number): Term

    /**
     * Divides this value by the term value.
     * @param term A [Term]
     * @return [Expression]
     */
    operator fun div(term: Term): Term

//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}