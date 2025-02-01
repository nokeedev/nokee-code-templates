package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedNativeLibraryElement;
import dev.nokee.platform.jni.fixtures.CGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;

public final class CGreeterLib extends GreeterImplementationAwareSourceElement {
	@Override
	public SourceElement getElementUsingGreeter() {
		return new CGreetUsingGreeter().asLib();
	}

	@Override
	public SourceElement getGreeter() {
		return new CGreeter().asLib();
	}

	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(subproject(subprojectPath)));
	}

	public SourceElement withGenericTestSuite() {
		return ofElements(getElementUsingGreeter(), getGreeter(), new CGreeterTest());
	}

	@SourceProject("templates-c-greeter/c-greeter-lib")
	public static class CGreetUsingGreeter extends DelegatedNativeLibraryElement {}
}
