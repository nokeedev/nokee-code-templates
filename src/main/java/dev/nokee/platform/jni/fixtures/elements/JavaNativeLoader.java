package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.SourceFileElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.nokee.platform.Elements;

import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;

@SourceProject(value = "templates-jni-greeter/java-jni-greeter", includes = {"src/main/java/com/example/greeter/NativeLoader.java"}, properties = {
	@SourceFileProperty(regex = "^package (com\\.example\\.greeter);$", name = "package")
})
public final class JavaNativeLoader extends SourceFileElement {
	private final JavaPackage javaPackage;

	@Override
	public SourceFile getSourceFile() {
		SourceFile result = Elements.sourceFileOf(JavaNativeLoader.class)
			.with("package", javaPackage.getName())
			.getSourceFile();
		return new SourceFile("java/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
	}

	public JavaNativeLoader() {
		this(ofPackage("com.example.greeter"));
	}

	public JavaNativeLoader(JavaPackage javaPackage) {
		this.javaPackage = javaPackage;
	}
}
