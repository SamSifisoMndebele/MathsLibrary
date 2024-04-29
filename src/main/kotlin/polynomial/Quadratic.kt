package com.ssmnd.polynomial

import com.ssmnd.util.Utils
import kotlin.math.sqrt

object Quadratic {

    fun solveRoots(a: Double, b: Double, c: Double) {
        val delta = (b * b - 4 * a * c) / (a * a)
        if (delta >= 0) solveRealRoots(delta, a, b)
        else solveImmRoots(delta, a, b)
    }

    private fun solveRealRoots(delta: Double, a: Double, b: Double, label1: String = "x1", label2: String = "x2") {
        println("Real roots:-")
        val x1 = (-b / a + sqrt(delta)) / 2
        val x2 = (-b / a - sqrt(delta)) / 2

        if (x1 <= x2) {
            println("$label1 = $x1")
            println("$label2 = $x2")
        } else {
            println("$label1 = $x2")
            println("$label2 = $x1")
        }
    }

    fun solveImmRoots(delta: Double, a: Double, b: Double, label1: String? = "x1", label2: String? = "x2") {
        println("Non-real roots:- i^2 = -1")
        val alpha = (-b / a) / 2
        val beta = sqrt(-delta) / 2

        if (alpha == 0.0) {
            println("$label1 = -${Utils.toFractionString(beta)}i")
            println("$label2 = ${Utils.toFractionString(beta)}i")
        } else {
            println("$label1 = ${Utils.toFractionString(alpha)} - ${Utils.toFractionString(beta)}i")
            println("$label1 = ${Utils.toFractionString(alpha)} + ${Utils.toFractionString(beta)}i")
        }
    }
}
