package de.musichin.ktlint.reporter.jsonlines

import com.pinterest.ktlint.cli.reporter.core.api.ReporterProviderV2
import java.io.PrintStream

class JsonLinesReporterProvider : ReporterProviderV2<JsonLinesReporter> {
    override val id: String = "jsonlines"

    override fun get(
        out: PrintStream,
        opt: Map<String, String>,
    ): JsonLinesReporter = JsonLinesReporter(out)
}
