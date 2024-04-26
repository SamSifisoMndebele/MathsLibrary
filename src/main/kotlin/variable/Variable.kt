package com.ssmnd.variable

import com.ssmnd.util.Latex

/**
 * The `Variable` class represents a mathematical variable with a character base and an exponent.
 * All mathematical variables, such as `x, y^2, x^y or 2^n`, are
 * implemented as instances of this class.
 */
interface Variable<B, E> : Comparable<Variable<B, E>>{
    val base: B
    val exponent: E
    /**
     * String representation of the variable.
     * @return [String]
     */
    override fun toString(): String

    /**
     * Latex representation of the variable.
     * @return [Latex]
     */
    fun toLatex(): Latex

    fun  abc() : Int

    /**
     * Returns the value of the variable.
     * @param value variable value, 0.0 by default.
     * @return [Double]
     */
    infix fun value(value: Number) : Double

    operator fun set(base: B, value: E)

    /**
     * Returns the positive of this value.
     * @return [Variable]
     */
    operator fun unaryPlus(): Variable<B, E> = this

    /**
     * Multiplies this variable by the other variable.
     * @param variable other [Variable]
     * @return [Variable]
     * @throws [ArithmeticException] If this variable is not the same as other variable
     */
    @Throws(ArithmeticException::class)
    operator fun times(variable: Variable<B, E>): Variable<B, E>

    /**
     * Divides this variable by the other variable.
     * @param variable other [Variable]
     * @return [Variable]
     * @throws [ArithmeticException] If this variable is not the same as other variable
     */
    @Throws(ArithmeticException::class)
    operator fun div(variable: Variable<B, E>): Variable<B, E>

    /**
     * Returns this variable to the power of the value `n`.
     * @param n exponent.
     * @return [Variable]
     */
    infix fun pow(n: Double): Variable<B, E>

    /**
     * Returns the derivative of this variable.
     * @param n the degree if the derivative. `e.g n = 1 for first derivative, n = 2 for second derivative.`
     * @return [Variable]
     */
    infix fun derivative(n: Int): Variable<B, E>



//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}