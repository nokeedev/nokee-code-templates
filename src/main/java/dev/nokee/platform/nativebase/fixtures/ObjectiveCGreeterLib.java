package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedNativeLibraryElement;
import dev.nokee.platform.jni.fixtures.ObjectiveCGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;

public final class ObjectiveCGreeterLib extends GreeterImplementationAwareSourceElement {
	@Override
	public SourceElement getElementUsingGreeter() {
		return new ObjectiveCGreetUsesGreeter().asLib();
	}

	@Override
	public SourceElement getGreeter() {
		return new ObjectiveCGreeter().asLib();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(subproject(subprojectPath)));
	}

	@SourceProject("templates-objc-greeter/objc-greeter-lib")
	private static class ObjectiveCGreetUsesGreeter extends DelegatedNativeLibraryElement {}
}
