package dev.nokee.platform.jni.fixtures.elements;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.JavaPackage;
import dev.nokee.elements.core.JvmSourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.core.SourceFileElement;

import java.nio.file.Paths;

import static dev.nokee.platform.jni.fixtures.elements.JavaUtils.replace;

@AutoElement(className = "JavaGreeter")
public abstract class JavaGreeterElement extends SourceFileElement implements JvmSourceElement {
	@ElementFileTree(value = "templates-jni-greeter/java-greeter/src/main/java/", includes = {"com/example/greeter/Greeter.java"})
	public abstract SourceFile getSourceFile();

	public SourceFileElement withPackage(JavaPackage javaPackage) {
		return new SourceFileElement() {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = JavaGreeterElement.this.getSourceFile();
				return sourceFile.withPath(it -> Paths.get(javaPackage.getDirectoryLayout()).resolve(it.getFileName())).withContent(replace("^package (com\\\\.example\\\\.greeter);$", javaPackage.getName()));
			}
		};
	}
}
