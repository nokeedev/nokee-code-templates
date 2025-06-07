package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.SourceFileElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.gradleplugins.fixtures.sources.DelegatedElements;

import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;

@SourceProject(value = "templates-jni-greeter/java-jni-app", includes = {"src/main/java/com/example/app/Main.java"}, properties = {
	@SourceFileProperty(regex = "^package (com\\.example\\.app);$", name = "package")
})
public final class JavaMainUsesGreeter extends SourceFileElement implements GreeterElement {
	private final JavaPackage javaPackage;

	@Override
	public SourceFile getSourceFile() {
		SourceFile result = DelegatedElements.sourceFileOf(JavaMainUsesGreeter.class)
			.with("package", javaPackage.getName())
			.getSourceFile();
		return new SourceFile("java/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
	}


    public JavaMainUsesGreeter() {
		JavaPackage javaPackage = ofPackage("com.example.app");
		this.javaPackage = javaPackage;
	}

	@Override
	public String getExpectedOutput() {
		return "Bonjour, World!";
	}
}
