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

package dev.nokee.platform.jni.fixtures;


import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.nativebase.NativeLibraryElement;

import java.util.List;

@AutoElement(className = "CGreeter")
public abstract class CGreeterElement extends NativeLibraryElement {
	@ElementFileTree(value = "templates-c-greeter/c-greeter/src/main/public")
	public abstract SourceElement getPublicHeaders();

	@ElementFileTree(value = "templates-c-greeter/c-greeter/src/main/c")
	public abstract SourceElement getSources();

	public NativeLibraryElement withOptionalFeature() {
		return withSources(new CGreeterElement_WithOptionalFeatureElement());
	}

	@AutoElement
	static abstract class WithOptionalFeature extends SourceElement {
		@ElementFileTree(value = "templates-c-greeter/c-greeter-with-optional-feature/src/main/c")
		public abstract List<SourceFile> getFiles();
	}
}
