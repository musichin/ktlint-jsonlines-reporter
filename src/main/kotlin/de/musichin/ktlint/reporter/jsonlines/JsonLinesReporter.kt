package de.musichin.ktlint.reporter.jsonlines

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.core.Reporter
import java.io.PrintStream

class JsonLinesReporter(
    private val out: PrintStream,
) : Reporter {
    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (corrected) return

        val line = err.line
        val column = err.col
        val message = err.detail
        val rule = err.ruleId
        out.run {
            print("{\"file\": \"")
            file.forEach { char -> print(char.escape()) }
            print("\", \"line\": ")
            print(line)
            print(", \"column\": ")
            print(column)
            print(", \"message\": \"")
            message.forEach { char -> print(char.escape()) }
            print("\", \"rule\": \"")
            print(rule)
            print("\"}\n")
        }
    }

    private fun Char.escape(): String = when (this) {
        '\"' -> "\\\""
        '\\' -> "\\\\"
        '/' -> "\\/"
        '\b' -> "\\b"
        '\u000C' -> "\\f"
        '\n' -> "\\n"
        '\r' -> "\\r"
        '\t' -> "\\t"
        in '\u0000'..'\u001F' -> escapeCode()
        else -> toString()
    }

    private fun Char.escapeCode(): String {
        val hex = code.toString(16)
        return buildString {
            append("\\u")
            repeat(4 - hex.length) {
                append("0")
            }
            append(hex)
        }
    }
}
