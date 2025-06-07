package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.SourceFileElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.gradleplugins.fixtures.sources.DelegatedElements;

import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;

@SourceProject(value = "templates-jni-greeter/java-jni-greeter", includes = {"src/test/java/com/example/greeter/GreeterTest.java"}, properties = {
	@SourceFileProperty(regex = "^package (com\\.example\\.greeter);$", name = "package")
})
public final class JavaGreeterJUnitTest extends SourceFileElement {
	private final JavaPackage javaPackage;

	@Override
	public SourceFile getSourceFile() {
		SourceFile result = DelegatedElements.sourceFileOf(JavaGreeterJUnitTest.class)
			.with("package", javaPackage.getName())
			.getSourceFile();
		return new SourceFile("java/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
	}

	@Override
	public String getSourceSetName() {
		return "test";
	}

	public JavaGreeterJUnitTest() {
		this(ofPackage("com.example.greeter"));
	}

	public JavaGreeterJUnitTest(JavaPackage javaPackage) {
		this.javaPackage = javaPackage;
	}
}
