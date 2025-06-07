/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.gradleplugins.fixtures.sources.DelegatedElements;

@SourceProject(value = "templates-jni-greeter/java-jni-greeter", includes = {"src/main/headers/com_example_greeter_Greeter.h"}, properties = {
	@SourceFileProperty(regex = "_Included_(com_example_greeter_Greeter)$", name = "headerGuard"),
	@SourceFileProperty(regex = "Class:\\s+(com_example_greeter_Greeter)$", name = "className"),
	@SourceFileProperty(regex = "\\s+(Java_com_example_greeter_Greeter_sayHello)", name = "methodName")
})
public final class GreeterJniHeader {
	public SourceFile withPackage(JavaPackage javaPackage) {
		return DelegatedElements.sourceFileOf(GreeterJniHeader.class)
			.with("headerGuard", javaPackage.getName().replace('.', '_'))
			.with("className", javaPackage.getName().replace('.', '_'))
			.with("methodName", javaPackage.jniMethodName("Greeter", "sayHello"))
			.with("name", javaPackage.jniHeader("Greeter"))
			.getSourceFile();
	}
}
