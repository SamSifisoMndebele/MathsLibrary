package com.ssmnd.expressions.expression

class ExpressionImpl private constructor(terms: Array<Int>)  {
    /*override val terms: Array<Term> = terms.ifEmpty { arrayOf(ExpTerm.ZERO) }

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
    }*/

}