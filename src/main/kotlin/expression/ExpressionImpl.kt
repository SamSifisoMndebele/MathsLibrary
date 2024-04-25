package com.ssmnd.expression

import com.ssmnd.terms.ExpTerm
import com.ssmnd.terms.Term

class ExpressionImpl private constructor(terms: Array<Term>) : Expression {
    override val terms: Array<Term> = terms.ifEmpty { arrayOf(ExpTerm.ZERO) }

    override fun toString(): String {
        val string = StringBuilder()
        string.append(terms[0].toString())
        for (i in 1 until terms.size) {
            val term = terms[i]
            if (term.coefficient > 0) string.append(" + ")
                .append(term.toString())
            else string.append(" - ")
                .append(term.toString().replaceFirst("-",""))
        }
        return string.toString()
    }

    override fun value(vararg vars: Pair<Char, Number>): Double {
        var value = 0.0
        for (term in terms) {
            value += term.value(*vars)
        }
        return value
    }

    override fun derivative(n: Int): Expression {
        TODO("Not yet implemented")
    }

    override fun plus(expression: Expression): Expression {
        TODO("Not yet implemented")
    }

    override fun minus(expression: Expression): Expression {
        TODO("Not yet implemented")
    }

    override fun times(expression: Expression): Expression {
        TODO("Not yet implemented")
    }

    override fun div(expression: Expression): Expression {
        TODO("Not yet implemented")
    }

    companion object {
        fun expressionOf(vararg terms : Term) : Expression {
            return ExpressionImpl(arrayOf(*terms))
        }
    }
}