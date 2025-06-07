package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.jni.fixtures.CGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

import static dev.gradleplugins.fixtures.sources.NativeElements.lib;
import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;

public final class CGreeterApp extends GreeterImplementationAwareSourceElement {
	@Override
	public SourceElement getElementUsingGreeter() {
		return new CMainUsesGreeter();
	}

	@Override
	public SourceElement getGreeter() {
		return new CGreeter();
	}

	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectName) {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(lib()).as(subproject(subprojectName)));
	}

	public SourceElement withGenericTestSuite() {
		return ofElements(getElementUsingGreeter(), getGreeter(), new CGreeterTest());
	}

	@SourceProject("templates-c-greeter/c-greeter-app")
	private static final class CMainUsesGreeter extends FromResource {}
}
