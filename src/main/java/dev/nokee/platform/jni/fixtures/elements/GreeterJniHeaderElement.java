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

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.JavaPackage;
import dev.nokee.elements.core.JvmSourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.core.SourceFileElement;

import java.util.function.UnaryOperator;

import static dev.nokee.platform.jni.fixtures.elements.JavaUtils.replace;

@AutoElement(className = "GreeterJniHeader")
public abstract class GreeterJniHeaderElement extends SourceFileElement implements JvmSourceElement {
	@ElementFileTree(value = "templates-jni-greeter/java-jni-greeter/src/main/headers", includes = {"com_example_greeter_Greeter.h"})
	public abstract SourceFile getSourceFile();

	public SourceFileElement withPackage(JavaPackage javaPackage) {
		return new SourceFileElement() {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = GreeterJniHeaderElement.this.getSourceFile();
				return sourceFile
					.withName(javaPackage.jniHeader("Greeter"))
					.withContent(headerGuard(javaPackage))
					.withContent(className(javaPackage))
					.withContent(methodName(javaPackage));
			}
		};
	}

	private static UnaryOperator<String> headerGuard(JavaPackage javaPackage) {
		return replace("_Included_(com_example_greeter_Greeter)$", javaPackage.getName().replace('.', '_'));
	}

	private static UnaryOperator<String> className(JavaPackage javaPackage) {
		return replace("Class:\\s+(com_example_greeter_Greeter)$", javaPackage.getName().replace('.', '_'));
	}

	private static UnaryOperator<String> methodName(JavaPackage javaPackage) {
		return replace("\\s+(Java_com_example_greeter_Greeter_sayHello)", javaPackage.jniMethodName("Greeter", "sayHello"));
	}
}
