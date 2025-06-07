package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.SourceFileElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.gradleplugins.fixtures.sources.java.JavaPackage;
import dev.gradleplugins.fixtures.sources.DelegatedElements;

@SourceProject(value = "templates-jni-greeter/java-jni-greeter", includes = {"src/main/java/com/example/greeter/Greeter.java"}, properties = {
	@SourceFileProperty(regex = "^package (com\\.example\\.greeter);$", name = "package"),
	@SourceFileProperty(regex = "\"(\\$\\{resourcePath\\}\\$\\{sharedLibraryBaseName\\})\"", name = "libName")
})
public final class JavaNativeGreeter extends SourceFileElement {
	private final JavaPackage javaPackage;
	private final String sharedLibraryBaseName;
	private final String resourcePath;

	@Override
	public SourceFile getSourceFile() {
		SourceFile result = DelegatedElements.sourceFileOf(JavaNativeGreeter.class)
			.with("package", javaPackage.getName())
			.with("libName", resourcePath + sharedLibraryBaseName)
			.getSourceFile();
		return new SourceFile("java/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
	}

	public JavaNativeGreeter(JavaPackage javaPackage, String sharedLibraryBaseName) {
		this(javaPackage, sharedLibraryBaseName, "");
	}

	public JavaNativeGreeter(JavaPackage javaPackage, String sharedLibraryBaseName, String resourcePath) {
		this.javaPackage = javaPackage;
		this.sharedLibraryBaseName = sharedLibraryBaseName;
		this.resourcePath = resourcePath;
	}

	public JavaNativeGreeter withSharedLibraryBaseName(String sharedLibraryBaseName) {
		return new JavaNativeGreeter(javaPackage, sharedLibraryBaseName, resourcePath);
	}

	public JavaNativeGreeter withResourcePath(String resourcePath) {
		return new JavaNativeGreeter(javaPackage, sharedLibraryBaseName, resourcePath);
	}
}
