package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.nokee.platform.Elements;
import dev.nokee.platform.jni.fixtures.JniBindingElement;

@SourceProject(value = "templates-jni-greeter/jni-cpp-greeter", includes = {"src/main/cpp/greeter.cpp"}, properties = {
	@SourceFileProperty(regex = "^#include\\s+\"(com_example_greeter_Greeter.h)\"$", name = "jniHeader"),
	@SourceFileProperty(regex = "\\s+(Java_com_example_greeter_Greeter_sayHello)\\(", name = "methodName")
})
public final class CppGreeterJniBinding extends JniBindingElement {
	private final JavaPackage javaPackage;

	public CppGreeterJniBinding(JavaPackage javaPackage) {
		this.javaPackage = javaPackage;
	}

	@Override
	public SourceFile getSourceFile() {
		return Elements.sourceFileOf(CppGreeterJniBinding.class)
			.with("methodName", javaPackage.jniMethodName("Greeter", "sayHello"))
			.with("jniHeader", javaPackage.jniHeader("Greeter"))
			.getSourceFile();
	}

	@Override
	public SourceFile getJniGeneratedHeaderFile() {
		return new GreeterJniHeader().withPackage(javaPackage);
	}
}
