package de.musichin.ktlint.reporter.jsonlines

import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.BASELINE_IGNORED
import com.pinterest.ktlint.cli.reporter.core.api.KtlintCliError.Status.FORMAT_IS_AUTOCORRECTED
import com.pinterest.ktlint.cli.reporter.core.api.ReporterV2
import java.io.PrintStream

class JsonLinesReporter(
    private val out: PrintStream,
) : ReporterV2 {
    override fun onLintError(
        file: String,
        ktlintCliError: KtlintCliError,
    ) {
        if (ktlintCliError.status == FORMAT_IS_AUTOCORRECTED) return
        if (ktlintCliError.status == BASELINE_IGNORED) return

        val line = ktlintCliError.line
        val column = ktlintCliError.col
        val message = escape(ktlintCliError.detail)
        val rule = escape(ktlintCliError.ruleId)
        out.print(
            "{\"file\": \"${escape(file)}\", \"line\": $line, \"column\": $column, \"message\": \"$message\", \"rule\": \"$rule\"}\n",
        )
    }

    private fun escape(data: String): String = buildString { data.forEach { char -> append(char.escape()) } }

    private fun Char.escape(): String =
        when {
            this == '\"' -> "\\\""
            this == '\\' -> "\\\\"
            this == '\b' -> "\\b"
            this == '\u000C' -> "\\f"
            this == '\n' -> "\\n"
            this == '\r' -> "\\r"
            this == '\t' -> "\\t"
            this < ' ' -> "\\u%04x".format(code)
            else -> toString()
        }
}
