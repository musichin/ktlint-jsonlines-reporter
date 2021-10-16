package de.musichin.ktlint.reporter.jsonlines

import com.pinterest.ktlint.core.ReporterProvider
import java.io.PrintStream

class JsonLinesReporterProvider : ReporterProvider {
    override val id: String = "jsonlines"

    override fun get(out: PrintStream, opt: Map<String, String>): JsonLinesReporter =
        JsonLinesReporter(out)
}
