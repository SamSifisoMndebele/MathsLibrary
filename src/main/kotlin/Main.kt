package com.ssmnd

import com.ssmnd.variable.ExponentialVariable
import com.ssmnd.variable.PolynomialVariable

private fun main() {

    val v1 = PolynomialVariable('x')
    val v2 = PolynomialVariable('x', 5)

    val e1 = ExponentialVariable(2,'x')
    val e2 = ExponentialVariable(3,'x')

    println(v1.derivative(2).toString())
    println(v2.derivative(2).toString())

    println(e1.derivative(1).toString())
    println(e2.derivative(2).toString())

    v1[4.toChar()] = 5.toDouble()

}