//package dev.nokee.platform.jni.fixtures;
//
//import dev.gradleplugins.fixtures.sources.SourceElement;
//import dev.gradleplugins.fixtures.sources.SourceFile;
//import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
//import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
//import dev.gradleplugins.fixtures.sources.java.JavaPackage;
//import dev.gradleplugins.fixtures.sources.DelegatedElements;
//import dev.nokee.platform.jni.fixtures.elements.*;
//
//import static dev.gradleplugins.fixtures.sources.NativeElements.lib;
//import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;
//import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;
//
//public final class JavaJniObjectiveCGreeterLib extends GreeterImplementationAwareSourceElement implements JniLibraryElement {
//	private final ObjectiveCGreeterJniBinding nativeBindings;
//	private final SourceElement jvmBindings;
//	private final SourceElement jvmImplementation;
//	private final ObjectiveCGreeter nativeImplementation;
//
//	@Override
//	public SourceElement getJvmSources() {
//		return ofElements(jvmBindings, jvmImplementation);
//	}
//
//	@Override
//	public SourceElement getNativeSources() {
//		return ofElements(nativeBindings, nativeImplementation);
//	}
//
//	@Override
//	public SourceElement withJUnitTest() {
//		return ofElements(this, new JavaGreeterJUnitTest());
//	}
//
//	public JavaJniObjectiveCGreeterLib(String projectName) {
//		this(ofPackage("com.example.greeter"), projectName);
//	}
//
//	private JavaJniObjectiveCGreeterLib(JavaPackage javaPackage, String sharedLibraryBaseName) {
//		this(new JavaNativeGreeter().withPackage(javaPackage).withSharedLibraryBaseName(sharedLibraryBaseName), new ObjectiveCGreeterJniBinding(javaPackage), new JavaNativeLoaderElement(javaPackage), new ObjectiveCGreeter());
//	}
//
//	private JavaJniObjectiveCGreeterLib(JavaNativeGreeterElement jvmBindings, ObjectiveCGreeterJniBinding nativeBindings, JavaNativeLoaderElement jvmImplementation, ObjectiveCGreeter nativeImplementation) {
//		this.jvmBindings = jvmBindings;
//		this.nativeBindings = nativeBindings;
//		this.jvmImplementation = jvmImplementation;
//		this.nativeImplementation = nativeImplementation;
//	}
//
//	@Override
//	public SourceElement getElementUsingGreeter() {
//		return ofElements(jvmBindings, nativeBindings, jvmImplementation);
//	}
//
//	@Override
//	public SourceElement getGreeter() {
//		return nativeImplementation;
//	}
//
//	public SourceElement withoutNativeImplementation() {
//		return ofElements(getJvmSources(), nativeBindings);
//	}
//
//	public SourceElement withFoundationFrameworkDependency() {
//		return ofElements(ofElements(getJvmSources(), new JavaGreeterJUnitTest()),
//			ofElements(nativeBindings, nativeImplementation.withFoundationFrameworkImplementation()));
//	}
//
//	public SourceElement withOptionalFeature() {
//		return ofElements(getJvmSources(), ofElements(nativeBindings, nativeImplementation.withOptionalFeature()));
//	}
//
//	@Override
//	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
//		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), nativeImplementation.as(lib()).as(subproject(subprojectPath)));
//	}
//
//	@SourceProject(value = "templates-jni-greeter/jni-objc-greeter", includes = {"src/main/objc/greeter.m"}, properties = {
//		@SourceFileProperty(regex = "^#include\\s+\"(com_example_greeter_Greeter.h)\"$", name = "jniHeader"),
//		@SourceFileProperty(regex = "\\s+(Java_com_example_greeter_Greeter_sayHello)\\(", name = "methodName")
//	})
//	private static class ObjectiveCGreeterJniBinding extends JniBindingElement {
//		private final JavaPackage javaPackage;
//
//		public ObjectiveCGreeterJniBinding(JavaPackage javaPackage) {
//			this.javaPackage = javaPackage;
//		}
//
//		@Override
//		public SourceFile getSourceFile() {
//			return DelegatedElements.sourceFileOf(ObjectiveCGreeterJniBinding.class)
//				.with("jniHeader", javaPackage.jniHeader("Greeter"))
//				.with("methodName", javaPackage.jniMethodName("Greeter", "sayHello"))
//				.getSourceFile();
//		}
//
//
//		@Override
//		public SourceFile getJniGeneratedHeaderFile() {
//			return new GreeterJniHeaderElement().withPackage(javaPackage);
//		}
//	}
//}
