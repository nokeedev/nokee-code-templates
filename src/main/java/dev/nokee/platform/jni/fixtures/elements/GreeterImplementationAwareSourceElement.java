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

import dev.nokee.elements.core.Element;
import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.WorkspaceElement;

import java.util.Arrays;
import java.util.List;

public interface GreeterImplementationAwareSourceElement {
	Element getElementUsingGreeter();

	Element getGreeter();

	ImplementationAsSubprojectElement withImplementationAsSubproject();


	final class ImplementationAsSubprojectElement extends WorkspaceElement {
		private final ProjectElement elementUsingGreeter;
		private final ProjectElement greeter;

		public ImplementationAsSubprojectElement(Element elementUsingGreeter, Element greeter) {
			this.elementUsingGreeter = ProjectElement.ofMain(elementUsingGreeter);
			this.greeter = ProjectElement.ofMain(greeter);
		}

		public ProjectElement getElementUsingGreeter() {
			return elementUsingGreeter;
		}

		public ProjectElement getGreeter() {
			return greeter;
		}

		@Override
		public List<ProjectElement> getProjects() {
			return Arrays.asList(getElementUsingGreeter(), getGreeter());
		}
	}
}
