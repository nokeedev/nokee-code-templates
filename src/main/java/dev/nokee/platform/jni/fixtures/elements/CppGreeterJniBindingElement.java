package dev.nokee.platform.jni.fixtures.elements;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.JavaPackage;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.core.SourceFileElement;
import dev.nokee.platform.jni.fixtures.JniBindingElement;

import java.util.function.UnaryOperator;

import static dev.nokee.platform.jni.fixtures.elements.JavaUtils.replace;

@AutoElement(className = "CppGreeterJniBinding")
public abstract class CppGreeterJniBindingElement extends JniBindingElement {
	private final JavaPackage javaPackage;

	public CppGreeterJniBindingElement() {
		this(JavaPackage.ofPackage("com.example.greeter"));
	}

	private CppGreeterJniBindingElement(JavaPackage javaPackage) {
		this.javaPackage = javaPackage;
	}

	@ElementFileTree(value = "templates-jni-greeter/jni-cpp-greeter/src/main/cpp", includes = {"greeter.cpp"})
	public abstract SourceFile getSourceFile();

	public CppGreeterJniBindingElement withPackage(JavaPackage javaPackage) {
		return new CppGreeterJniBindingElement(javaPackage) {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = CppGreeterJniBindingElement.this.getSourceFile();
				return sourceFile
					.withContent(jniHeader(javaPackage))
					.withContent(methodName(javaPackage));
			}
		};
	}

	private static UnaryOperator<String> methodName(JavaPackage javaPackage) {
		return replace("\\s+(Java_com_example_greeter_Greeter_sayHello)\\(", javaPackage.jniMethodName("Greeter", "sayHello"));
	}

	private static UnaryOperator<String> jniHeader(JavaPackage javaPackage) {
		return replace("^#include\\s+\"(com_example_greeter_Greeter.h)\"$", javaPackage.jniHeader("Greeter"));
	}

	@Override
	public SourceFileElement getJniGeneratedHeaderFile() {
		return new GreeterJniHeader().withPackage(javaPackage);
	}
}
