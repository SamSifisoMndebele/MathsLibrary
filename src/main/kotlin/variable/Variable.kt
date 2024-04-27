package com.ssmnd.variable

import com.ssmnd.term.Term
import com.ssmnd.util.Latex

/**
 * The `Variable` class represents a mathematical variable with a character base and an exponent.
 * All mathematical variables, such as `x, y^2, x^y or 2^n`, are
 * implemented as instances of this class.
 */
interface Variable : Comparable<Variable> {
    /**
     * The base of the variable
     */
    val base: Any
    /**
     * The exponent of the variable
     */
    val exponent: Any
    /**
     * String representation of the variable.
     * @return [String]
     */
    override fun toString(): String

    val comparableString : String
    override fun compareTo(other: Variable): Int = comparableString.compareTo(other.comparableString)

    /**
     * Latex representation of the variable.
     * @return [Latex]
     */
    fun toLatex(): Latex

    /**
     * Returns the value of the variable.
     * @param x variable value, 0.0 by default.
     * @return [Double]
     */
    infix fun value(x: Number) : Double

    @Deprecated("This method may throw runtime errors.")
    @Throws(NumberFormatException::class)
    operator fun set(base: Any, value: Any)

    /**
     * Returns the positive of this value.
     * @return [Variable]
     */
    operator fun unaryPlus(): Variable = this

    /**
     * Multiplies this variable by the other variable.
     * @param variable other [Variable]
     * @return [Variable]
     * @throws [ArithmeticException] If this variable is not the same as other variable
     */
    @Throws(ArithmeticException::class)
    operator fun times(variable: Variable): Variable

    /**
     * Divides this variable by the other variable.
     * @param variable other [Variable]
     * @return [Variable]
     * @throws [ArithmeticException] If this variable is not the same as other variable
     */
    @Throws(ArithmeticException::class)
    operator fun div(variable: Variable): Variable

    /**
     * Returns this variable to the power of the value `n`.
     * @param n exponent.
     * @return [Variable]
     */
    infix fun pow(n: Double): Variable

    /**
     * Returns the derivative of this variable.
     * @param n the degree if the derivative. `e.g n = 1 for first derivative, n = 2 for second derivative.`
     * @return [Term]
     */
    infix fun derivative(n: Int): Term


//    override fun equals(other: Any?): Boolean
//    override fun hashCode(): Int
}