package com.ssmnd.term

import com.ssmnd.util.Latex
import com.ssmnd.variable.Variable

class TermImpl<B, E>(override val coefficient: Double, override val variables: Set<Variable<B, E>>) : Term<Variable<B, E>> {
    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun toLatex(): Latex {
        TODO("Not yet implemented")
    }

    override fun value(vararg vars: Pair<Char, Number>): Double {
        val value = 0.0
        variables.forEach {

        }
        TODO("Not yet implemented")
    }

    override fun derivative(n: Int): Term<Variable<B, E>> {
        TODO("Not yet implemented")
    }

    override fun div(term: Term<Variable<B, E>>): Term<Variable<B, E>> {
        TODO("Not yet implemented")
    }

    override fun times(term: Term<Variable<B, E>>): Term<Variable<B, E>> {
        TODO("Not yet implemented")
    }

    override fun minus(term: Term<Variable<B, E>>): Term<Variable<B, E>> {
        TODO("Not yet implemented")
    }

    override fun plus(term: Term<Variable<B, E>>): Term<Variable<B, E>> {
        TODO("Not yet implemented")
    }
}