package com.ssmnd

import com.ssmnd.expression.ExpressionImpl.Companion.expressionOf
import com.ssmnd.terms.PolTerm.Companion.polTermOf

private fun main() {
    val e1 = expressionOf(
        polTermOf(-1),
        polTermOf('x'),
        polTermOf(-2, 'y'),
        polTermOf(3, 'k', 5)
    )

    println(e1.toString())

}