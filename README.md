# 🛠 ktlint JSON Lines reporter
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.21-blue.svg)](http://kotlinlang.org)
[![CI](https://github.com/musichin/ktlint-jsonlines-reporter/actions/workflows/ci.yml/badge.svg)](https://github.com/musichin/ktlint-jsonlines-reporter/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/musichin/ktlint-jsonlines-reporter/branch/main/graph/badge.svg?token=I2LXI4OUBT)](https://codecov.io/gh/musichin/ktlint-jsonlines-reporter)
[![Release](https://img.shields.io/github/v/release/musichin/ktlint-jsonlines-reporter)](https://github.com/musichin/ktlint-jsonlines-reporter/releases)
[![Maven Central](https://img.shields.io/maven-central/v/de.musichin.ktlint.reporter/ktlint-reporter-jsonlines)](https://central.sonatype.com/artifact/de.musichin.ktlint.reporter/ktlint-reporter-jsonlines)

A custom [ktlint](https://ktlint.github.io) reporter that writes lint issues as [JSON Lines](https://jsonlines.org), one JSON object per issue.

- 📄 Easy to process with `jq` or any line-based tooling
- 📦 Published to Maven Central

---

## 🚀 Quick Start

### ▶️ Using JAR
Download the latest JAR from the [Releases](https://github.com/musichin/ktlint-jsonlines-reporter/releases) page:

```bash
ktlint --reporter=jsonlines,artifact=ktlint-jsonlines-reporter.jar
```
Write the report to a file (recommended, as ktlint also logs to standard output):
```bash
ktlint --reporter=jsonlines,artifact=ktlint-jsonlines-reporter.jar,output=ktlint.jsonl
```

### 🛠 Using Maven or Gradle
The reporter is published to Maven Central as `de.musichin.ktlint.reporter:ktlint-reporter-jsonlines:x.y.z`.
Add it to the classpath ktlint runs with, not to your project's dependencies, and select it with the reporter id `jsonlines`.

## 🔗 Compatibility
| ktlint   | Reporter                |
|----------|-------------------------|
| 2.x      | latest 2.x *(upcoming)* |
| 1.x      | latest 1.x (≥ 1.2.0)    |
| 0.43.x   | 1.1.0                   |
| < 0.43.0 | 1.0.1                   |

The reporter's major version follows ktlint's major version.

## Example
For the Kotlin file:
```kotlin
data class Address(val street: String, val zip:String)
```
the following JSON lines are generated:
```json
{"file": "Address.kt", "line": 1, "column": 20, "message": "Newline expected after opening parenthesis", "rule": "standard:class-signature"}
{"file": "Address.kt", "line": 1, "column": 40, "message": "Parameter should start on a newline", "rule": "standard:class-signature"}
{"file": "Address.kt", "line": 1, "column": 47, "message": "Whitespace after ':' is missing", "rule": "standard:parameter-list-spacing"}
{"file": "Address.kt", "line": 1, "column": 48, "message": "Missing spacing after \":\"", "rule": "standard:colon-spacing"}
{"file": "Address.kt", "line": 1, "column": 54, "message": "File must end with a newline (\\n)", "rule": "standard:final-newline"}
{"file": "Address.kt", "line": 1, "column": 54, "message": "Newline expected before closing parenthesis", "rule": "standard:class-signature"}
```
Each line has the same fields as an entry of ktlint's built-in `json` reporter, but issues are streamed one per line instead of being grouped by file.
Autocorrected issues and issues suppressed by a baseline are not reported.

## 📝 License
MIT License © 2021 Anton Musichin
