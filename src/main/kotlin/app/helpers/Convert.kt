package app.helpers

object Convert {

    fun daysToMillis(days: Double): Long {
        return (1000 // Milliseconds
                * 60 // Seconds
                * 60 // Minutes
                * 24 // Hours
                * days).toLong()
    }

    fun daysToMillis(days: Int): Long {
        return daysToMillis(days.toDouble())
    }
}