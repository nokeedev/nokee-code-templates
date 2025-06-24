//package dev.nokee.platform.nativebase.fixtures;
//
//import dev.gradleplugins.fixtures.sources.SourceElement;
//import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
//import dev.nokee.platform.jni.fixtures.ObjectiveCppGreeter;
//import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;
//
//import static dev.gradleplugins.fixtures.sources.NativeElements.lib;
//import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;
//
//public final class ObjectiveCppGreeterApp extends GreeterImplementationAwareSourceElement {
//	@Override
//	public SourceElement getElementUsingGreeter() {
//		return new ObjectiveCppMainUsesGreeter();
//	}
//
//	@Override
//	public SourceElement getGreeter() {
//		return new ObjectiveCppGreeter();
//	}
//
//	@Override
//	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
//		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), getGreeter().as(lib()).as(subproject(subprojectPath)));
//	}
//
//	@SourceProject("templates-objcpp-greeter/objcpp-greeter-app")
//	private static final class ObjectiveCppMainUsesGreeter extends FromResource {}
//}
