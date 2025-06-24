package dev.nokee.platform.jni.fixtures.elements;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.JavaPackage;
import dev.nokee.elements.core.JvmSourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.core.SourceFileElement;

import java.nio.file.Paths;

import static dev.nokee.platform.jni.fixtures.elements.JavaUtils.replace;

@AutoElement(className = "JavaNativeGreeter")
public abstract class JavaNativeGreeterElement extends SourceFileElement implements JvmSourceElement {
	@ElementFileTree(value = "templates-jni-greeter/java-jni-greeter/src/main/java", includes = {"com/example/greeter/Greeter.java"})
	public abstract SourceFile getSourceFile();

	public JavaNativeGreeterElement withPackage(JavaPackage javaPackage) {
		return new JavaNativeGreeterElement() {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = JavaNativeGreeterElement.this.getSourceFile();
				return sourceFile.withPath(it -> Paths.get(javaPackage.getDirectoryLayout()).resolve(it.getFileName())).withContent(replace("^package (com\\\\.example\\\\.greeter);$", javaPackage.getName()));
			}
		};
	}

	public JavaNativeGreeterElement withSharedLibraryBaseName(String sharedLibraryBaseName) {
		return new JavaNativeGreeterElement() {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = JavaNativeGreeterElement.this.getSourceFile();
				return sourceFile.withContent(replace("^\\s+static final String sharedLibraryBaseName = \"([^\"]*)\";$", sharedLibraryBaseName));
			}
		};
	}

	public JavaNativeGreeterElement withResourcePath(String resourcePath) {
		return new JavaNativeGreeterElement() {
			@Override
			public SourceFile getSourceFile() {
				SourceFile sourceFile = JavaNativeGreeterElement.this.getSourceFile();
				return sourceFile.withContent(replace("^\\s+static final String resourcePath = \"([^\"]*)\";$", resourcePath));
			}
		};
	}
}
