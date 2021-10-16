# ktlint JSON lines reporter
[![Kotlin](https://img.shields.io/badge/Kotlin-1.5.31-blue.svg)](http://kotlinlang.org)
[![CI](https://github.com/musichin/ktlint-jsonlines-reporter/actions/workflows/ci.yml/badge.svg)](https://github.com/musichin/ktlint-jsonlines-reporter/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/musichin/ktlint-jsonlines-reporter/branch/main/graph/badge.svg?token=I2LXI4OUBT)](https://codecov.io/gh/musichin/ktlint-jsonlines-reporter)


## Usage
Download the jar and run:
```
ktlint --reporter=jsonlines,artifact=ktlint-jsonlines-reporter.jar
```

## Download
Either download the JAR directly from [Releases](https://github.com/musichin/ktlint-jsonlines-reporter/releases) section or use maven:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'de.musichin.ktlint.reporter:ktlint-reporter-jsonlines:x.y.z'
}
```

## Example
For kotlin file:
```kotlin
data class Address(val street: String, zip:String)
```
Following JSON lines will be generated:
```json
{"file": "Address.kt", "line": 1, "column": 1, "message": "File must end with a newline (\\n)", "rule": "final-newline"}
{"file": "Address.kt", "line": 1, "column": 44, "message": "Missing spacing after \":\"", "rule": "colon-spacing"}

```


## License

    MIT License

    Copyright (c) 2021 Anton Musichin

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

