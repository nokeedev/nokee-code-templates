//package dev.nokee.platform.nativebase.fixtures;
//
//import dev.gradleplugins.fixtures.sources.SourceElement;
//import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
//import dev.nokee.platform.jni.fixtures.ObjectiveCGreeter;
//import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;
//
//import static dev.gradleplugins.fixtures.sources.NativeElements.lib;
//import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;
//
//public final class ObjectiveCGreeterApp extends GreeterImplementationAwareSourceElement {
//	@Override
//	public SourceElement getElementUsingGreeter() {
//		return new ObjectiveCMainUsesGreeter();
//	}
//
//	@Override
//	public SourceElement getGreeter() {
//		return new ObjectiveCGreeter();
//	}
//
//	@Override
//	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
//		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(lib()).as(subproject(subprojectPath)));
//	}
//
//	@SourceProject("templates-objc-greeter/objc-greeter-app")
//	private static final class ObjectiveCMainUsesGreeter extends FromResource {}
//}
