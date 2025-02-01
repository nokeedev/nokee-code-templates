package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedNativeLibraryElement;
import dev.nokee.platform.jni.fixtures.ObjectiveCppGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;

public final class ObjectiveCppGreeterLib extends GreeterImplementationAwareSourceElement {
	@Override
	public SourceElement getElementUsingGreeter() {
		return new ObjectiveCppGreetUsesGreeter().asLib();
	}

	@Override
	public SourceElement getGreeter() {
		return new ObjectiveCppGreeter().asLib();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(subproject(subprojectPath)));
	}

	@SourceProject("templates-objcpp-greeter/objcpp-greeter-lib")
	private static class ObjectiveCppGreetUsesGreeter extends DelegatedNativeLibraryElement {}
}
