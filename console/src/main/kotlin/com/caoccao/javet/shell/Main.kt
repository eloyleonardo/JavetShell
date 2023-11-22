/*
 * Copyright (c) 2023. caoccao.com Sam Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.caoccao.javet.shell

import com.caoccao.javet.shell.constants.Constants
import com.caoccao.javet.shell.enums.ExitCode
import com.caoccao.javet.shell.enums.RuntimeType
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val argParser = ArgParser(Constants.Application.NAME)
    val runtimeType by argParser.option(
        ArgType.Choice<RuntimeType>(),
        shortName = "r",
        description = Constants.Application.DESCRIPTION
    ).default(RuntimeType.V8)
    argParser.parse(args)
    val javetShell = JavetShell(runtimeType.value)
    val exitCode =
        try {
            javetShell.run()
        } catch (t: Throwable) {
            t.printStackTrace()
            ExitCode.UnknownError
        }
    exitProcess(exitCode.code)
}
