package unit.helpers

import app.helpers.Convert
import org.junit.Assert
import org.junit.Test

class ConvertTest {

    @Test
    fun oneDayAndAHalfEqualsAmountInMillis() {
        Assert.assertEquals(Convert.daysToMillis(1.5), 129_600_000)
    }

    @Test
    fun ninetyDaysEqualsAmountInMillis() {
        Assert.assertEquals(Convert.daysToMillis(90), 7_776_000_000)
    }
}