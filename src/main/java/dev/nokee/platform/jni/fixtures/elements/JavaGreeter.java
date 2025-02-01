package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.SourceFileElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.nokee.platform.Elements;

import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;

@SourceProject(value = "templates-jni-greeter/java-greeter", includes = {"src/main/java/com/example/greeter/Greeter.java"}, properties = {
	@SourceFileProperty(regex = "^package\\s+(com.example.greeter);$", name = "package")
})
public final class JavaGreeter extends SourceFileElement {
	private final JavaPackage javaPackage;

	@Override
	public SourceFile getSourceFile() {
		SourceFile result = Elements.sourceFileOf(JavaGreeter.class)
			.with("package", javaPackage.getName())
			.getSourceFile();
		return new SourceFile("java/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
	}

	public JavaGreeter() {
		this(ofPackage("com.example.greeter"));
	}

	public JavaGreeter(JavaPackage javaPackage) {
		this.javaPackage = javaPackage;
	}
}
