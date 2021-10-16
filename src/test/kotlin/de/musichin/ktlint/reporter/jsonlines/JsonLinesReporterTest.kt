package de.musichin.ktlint.reporter.jsonlines

import com.pinterest.ktlint.core.LintError
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class JsonLinesReporterTest {

    @Test
    fun testReporterError() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = JsonLinesReporter(out)

        val lintError = LintError(1, 2, "ruleId", "\u0002details\u0003")
        reporter.before("test/file")
        reporter.onLintError("test/file", lintError, false)
        reporter.after("test/file")

        val result = stream.toString()
        assertEquals(
            """
            |{"file": "test\/file", "line": 1, "column": 2, "message": "\u0002details\u0003", "rule": "ruleId"}
            |
        """.trimMargin(),
            result
        )
    }

    @Test
    fun testIgnoringCorrected() {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val reporter = JsonLinesReporter(out)

        val lintError = LintError(1, 2, "testRuleId", "testDetail")
        reporter.before("testFile")
        reporter.onLintError("testFile", lintError, true)
        reporter.after("testFile")

        val result = stream.toString()
        assertEquals("", result)
    }
}
