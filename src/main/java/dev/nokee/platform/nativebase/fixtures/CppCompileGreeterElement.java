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

package dev.nokee.platform.nativebase.fixtures;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.SourceFile;

import java.util.List;

@AutoElement(className = "CppCompileGreeter")
public abstract class CppCompileGreeterElement extends SourceElement {
	@ElementFileTree("templates-cpp-greeter/cpp-compile-greeter/src/main/cpp")
	public abstract List<SourceFile> getFiles();
}
