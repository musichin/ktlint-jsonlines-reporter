package de.musichin.ktlint.reporter.jsonlines

import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class JsonLinesReporterProviderTest {

    @Test
    fun testGet() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = JsonLinesReporterProvider().get(out, emptyMap())

        assertNotNull(reporter)
    }

    @Test
    fun testId() {
        val id = JsonLinesReporterProvider().id

        assertEquals("jsonlines", id)
    }
}
