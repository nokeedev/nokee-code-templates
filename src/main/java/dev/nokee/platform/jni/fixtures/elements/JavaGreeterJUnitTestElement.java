package dev.nokee.platform.jni.fixtures.elements;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.*;

import java.nio.file.Paths;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import static dev.nokee.platform.jni.fixtures.elements.JavaUtils.replace;

@AutoElement(className = "JavaGreeterJUnitTest")
public abstract class JavaGreeterJUnitTestElement extends SourceFileElement implements JvmSourceElement {
	@ElementFileTree(value = "templates-jni-greeter/java-jni-greeter/src/test/java", includes = {"com/example/greeter/GreeterTest.java"})
	public abstract SourceFile getSourceFile();

	public SourceFileElement withPackage(JavaPackage javaPackage) {
		return new SourceFileElement() {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = JavaGreeterJUnitTestElement.this.getSourceFile();
				return sourceFile.withPath(it -> Paths.get(javaPackage.getDirectoryLayout()).resolve(it.getFileName())).withContent(replace("^package (com\\\\.example\\\\.greeter);$", javaPackage.getName()));
			}
		};
	}
}
