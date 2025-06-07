package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.jni.fixtures.elements.CppGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;

public final class CppGreeterLib extends GreeterImplementationAwareSourceElement {
	@Override
	public SourceElement getElementUsingGreeter() {
		return new CppGreetUsingGreeter().asLib();
	}

	@Override
	public SourceElement getGreeter() {
		return new CppGreeter().asLib();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(subproject(subprojectPath)));
	}

	public SourceElement withGenericTestSuite() {
		return ofElements(getElementUsingGreeter(), getGreeter(), new CppGreeterTest());
	}

	@SourceProject("templates-cpp-greeter/cpp-greeter-lib")
	private static class CppGreetUsingGreeter extends NativeLibraryElement.FromResource {}
}
