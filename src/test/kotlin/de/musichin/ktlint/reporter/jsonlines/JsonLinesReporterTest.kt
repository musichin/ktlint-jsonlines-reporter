package de.musichin.ktlint.reporter.jsonlines

import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.BASELINE_IGNORED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.FORMAT_IS_AUTOCORRECTED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.LINT_CAN_NOT_BE_AUTOCORRECTED
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonLinesReporterTest {
    @Test
    fun testReporterError() {
        assertEquals(
            """{"file": "testFile", "line": 1, "column": 2, "message": "testDetail", "rule": "testRuleId"}""",
            report(),
        )
    }

    @Test
    fun testIgnoringCorrected() {
        assertEquals("", report(status = FORMAT_IS_AUTOCORRECTED))
    }

    @Test
    fun testIgnoringBaseline() {
        assertEquals("", report(status = BASELINE_IGNORED))
    }

    @Test
    fun testMultipleErrors() {
        val stream = ByteArrayOutputStream()
        val reporter = JsonLinesReporter(PrintStream(stream))
        reporter.onLintError("a.kt", KtlintCliError(1, 2, "rule-a", "detail a", LINT_CAN_NOT_BE_AUTOCORRECTED))
        reporter.onLintError("b.kt", KtlintCliError(3, 4, "rule-b", "detail b", LINT_CAN_NOT_BE_AUTOCORRECTED))

        assertEquals(
            listOf(
                """{"file": "a.kt", "line": 1, "column": 2, "message": "detail a", "rule": "rule-a"}""",
                """{"file": "b.kt", "line": 3, "column": 4, "message": "detail b", "rule": "rule-b"}""",
            ),
            stream.toString().lines().filter { it.isNotEmpty() },
        )
    }

    @Test
    fun testEscapingFile() {
        assertEquals(
            """{"file": "C:\\dir/\"x\".kt", "line": 1, "column": 2, "message": "testDetail", "rule": "testRuleId"}""",
            report(file = "C:\\dir/\"x\".kt"),
        )
    }

    @Test
    fun testEscapingMessage() {
        assertEquals(
            """{"file": "testFile", "line": 1, "column": 2, "message": "\u0002a\b\f\n\r\tb\u001f\u0003", "rule": "testRuleId"}""",
            report(detail = "\u0002a\b\u000C\n\r\tb\u001F\u0003"),
        )
    }

    @Test
    fun testEscapingRuleId() {
        assertEquals(
            """{"file": "testFile", "line": 1, "column": 2, "message": "testDetail", "rule": "standard:\"rule\""}""",
            report(ruleId = "standard:\"rule\""),
        )
    }

    @Test
    fun testEmptyRuleId() {
        assertEquals(
            """{"file": "testFile", "line": 1, "column": 2, "message": "Not a valid Kotlin file", "rule": ""}""",
            report(ruleId = "", detail = "Not a valid Kotlin file"),
        )
    }

    private fun report(
        file: String = "testFile",
        ruleId: String = "testRuleId",
        detail: String = "testDetail",
        status: KtlintCliError.Status = LINT_CAN_NOT_BE_AUTOCORRECTED,
    ): String {
        val stream = ByteArrayOutputStream()
        val reporter = JsonLinesReporter(PrintStream(stream))
        reporter.onLintError(file, KtlintCliError(1, 2, ruleId, detail, status))
        return stream.toString().trim()
    }
}
