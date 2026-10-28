package de.musichin.ktlint.reporter.jsonlines

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JsonLinesReporterProviderTest {
    @Test
    fun testGet() {
        assertNotNull(JsonLinesReporterProvider().get(PrintStream(ByteArrayOutputStream()), emptyMap()))
    }

    @Test
    fun testId() {
        assertEquals("jsonlines", JsonLinesReporterProvider().id)
    }
}
