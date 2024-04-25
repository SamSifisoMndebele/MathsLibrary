package terms

import com.ssmnd.terms.PolTerm
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class PolTermTest {
    private val term0 = PolTerm.ZERO
    private val term1 = PolTerm(5)
    private val term2 = PolTerm(3, 'x', 2)
    private val term3 = PolTerm(-5, 'x', 2)


    @Test
    fun testToString() {
        /*assertEquals(term0.toString(), "0")
        assertEquals(term1.toString(), "1")
        assertEquals(term2.toString(), "3x^2")*/
    }

    @Test
    fun plus() {
        val expected = term2 + term3
        val actual = PolTerm(-2, 'x' ,2)
        assertEquals(expected, actual, "Wrong Variables")
    }

    @Test
    fun minus() {
    }

    @Test
    fun times() {
    }

    @Test
    fun div() {
    }
}