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
import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.core.SourceFileElement;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;
import dev.nokee.elements.nativebase.NativeSourceElement;
import dev.nokee.platform.jni.fixtures.elements.CppGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

public final class CppGreeterApp extends ProjectElement implements GreeterImplementationAwareSourceElement {
	@Override
	public NativeElement getMainElement() {
		return NativeSourceElement.ofElements(getGreeter().asImplementation(), NativeSourceElement.ofSources(getElementUsingGreeter()));
	}

	@Override
	public SourceElement getElementUsingGreeter() {
		return new CppGreeterApp_CppMainUsesGreeterElement();
	}

	@Override
	public NativeLibraryElement getGreeter() {
		return new CppGreeter();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject() {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), new CppGreeter());
	}

	public ProjectElement withGenericTestSuite() {
		return withTest(new CppGreeterTest());
	}

	@AutoElement
	static abstract class CppMainUsesGreeter extends SourceFileElement {
		@ElementFileTree("templates-cpp-greeter/cpp-greeter-app/src/main/cpp")
		public abstract SourceFile getSourceFile();
	}
}
