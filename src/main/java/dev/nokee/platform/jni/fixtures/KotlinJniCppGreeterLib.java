//package dev.nokee.platform.jni.fixtures;
//
//import dev.gradleplugins.fixtures.sources.NativeSourceElement;
//import dev.gradleplugins.fixtures.sources.SourceElement;
//import dev.gradleplugins.fixtures.sources.SourceFile;
//import dev.gradleplugins.fixtures.sources.SourceFileElement;
//import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
//import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
//import dev.gradleplugins.fixtures.sources.java.JavaPackage;
//import dev.gradleplugins.fixtures.sources.DelegatedElements;
//import dev.nokee.platform.jni.fixtures.elements.*;
//
//import static dev.gradleplugins.fixtures.sources.NativeElements.lib;
//import static dev.gradleplugins.fixtures.sources.NativeElements.subproject;
//import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;
//
//public final class KotlinJniCppGreeterLib extends GreeterImplementationAwareSourceElement implements JniLibraryElement {
//	private final NativeSourceElement nativeBindings;
//	private final KotlinNativeGreeter jvmBindings;
//	private final SourceElement jvmImplementation;
//	private final CppGreeterElement nativeImplementation;
//	private final SourceElement junitTest;
//	private /*final*/ String projectName;
//	private /*final*/ String resourcePath;
//
//	@Override
//	public SourceElement getJvmSources() {
//		return ofElements(jvmBindings, jvmImplementation);
//	}
//
//	@Override
//	public SourceElement getNativeSources() {
//		return ofElements(nativeBindings, nativeImplementation);
//	}
//
//	public KotlinJniCppGreeterLib(String projectName) {
//		this(projectName, "");
//	}
//
//	public KotlinJniCppGreeterLib(String projectName, String resourcePath) {
//		this(ofPackage("com.example.greeter"), projectName, resourcePath);
//		this.resourcePath = resourcePath;
//		this.projectName = projectName;
//	}
//
//	private KotlinJniCppGreeterLib(JavaPackage javaPackage, String sharedLibraryBaseName, String resourcePath) {
//		this(new KotlinNativeGreeter(javaPackage, sharedLibraryBaseName, resourcePath), new CppGreeterJniBindingElement(javaPackage).withJniGeneratedHeader(), new KotlinNativeLoader(javaPackage), new CppGreeterElement(), new KotlinGreeterJUnitTest(javaPackage));
//	}
//
//	private KotlinJniCppGreeterLib(KotlinNativeGreeter jvmBindings, NativeSourceElement nativeBindings, KotlinNativeLoader jvmImplementation, CppGreeterElement nativeImplementation, KotlinGreeterJUnitTest junitTest) {
//		this.jvmBindings = jvmBindings;
//		this.nativeBindings = nativeBindings;
//		this.jvmImplementation = jvmImplementation;
//		this.nativeImplementation = nativeImplementation;
//		this.junitTest = junitTest;
//	}
//
//	@Override
//	public SourceElement getElementUsingGreeter() {
//		return ofElements(jvmBindings, nativeBindings, jvmImplementation);
//	}
//
//	@Override
//	public SourceElement getGreeter() {
//		return nativeImplementation;
//	}
//
//	@Override
//	public SourceElement withJUnitTest() {
//		return ofElements(this, junitTest);
//	}
//
//	@Override
//	public ImplementationAsSubprojectElement withImplementationAsSubproject(String subprojectPath) {
//		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), nativeImplementation.as(lib()).as(subproject(subprojectPath)));
//	}
//
//	@SourceProject(value = "templates-jni-greeter/kotlin-jni-greeter", includes = {"src/main/kotlin/com/example/greeter/Greeter.kt"}, properties = {
//		@SourceFileProperty(regex = "^package (com\\.example\\.greeter)$", name = "package"),
//		@SourceFileProperty(regex = "\"(\\$\\{resourcePath\\}\\$\\{sharedLibraryBaseName\\})\"", name = "libName")
//	})
//	private static class KotlinNativeGreeter extends SourceFileElement {
//		private final JavaPackage javaPackage;
//		private final String sharedLibraryBaseName;
//		private final String resourcePath;
//
//		@Override
//		public SourceFile getSourceFile() {
//			SourceFile result = DelegatedElements.sourceFileOf(JavaNativeLoaderElement.class)
//				.with("package", javaPackage.getName())
//				.with("libName", resourcePath + sharedLibraryBaseName)
//				.getSourceFile();
//			return new SourceFile("kotlin/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
//		}
//
//		public KotlinNativeGreeter(JavaPackage javaPackage, String sharedLibraryBaseName) {
//			this(javaPackage, sharedLibraryBaseName, "");
//		}
//
//		public KotlinNativeGreeter(JavaPackage javaPackage, String sharedLibraryBaseName, String resourcePath) {
//			this.javaPackage = javaPackage;
//			this.sharedLibraryBaseName = sharedLibraryBaseName;
//			this.resourcePath = resourcePath;
//		}
//
//		public KotlinNativeGreeter withSharedLibraryBaseName(String sharedLibraryBaseName) {
//			return new KotlinNativeGreeter(javaPackage, sharedLibraryBaseName, resourcePath);
//		}
//
//		public KotlinNativeGreeter withResourcePath(String resourcePath) {
//			return new KotlinNativeGreeter(javaPackage, sharedLibraryBaseName, resourcePath);
//		}
//	}
//
//	@SourceProject(value = "templates-jni-greeter/kotlin-jni-greeter", includes = {"src/main/kotlin/com/example/greeter/NativeLoader.kt"}, properties = {
//		@SourceFileProperty(regex = "^package (com\\.example\\.greeter)$", name = "package"),
//	})
//	private static class KotlinNativeLoader extends SourceFileElement {
//		private final JavaPackage javaPackage;
//
//		@Override
//		public SourceFile getSourceFile() {
//			SourceFile result = DelegatedElements.sourceFileOf(KotlinNativeLoader.class)
//				.with("package", javaPackage.getName())
//				.getSourceFile();
//			return new SourceFile("kotlin/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
//		}
//
//		public KotlinNativeLoader(JavaPackage javaPackage) {
//			this.javaPackage = javaPackage;
//		}
//	}
//
//	@SourceProject(value = "templates-jni-greeter/kotlin-jni-greeter", includes = {"src/test/kotlin/com/example/greeter/GreeterTest.kt"}, properties = {
//		@SourceFileProperty(regex = "^package (com\\.example\\.greeter)$", name = "package"),
//	})
//	private static class KotlinGreeterJUnitTest extends SourceFileElement {
//		private final JavaPackage javaPackage;
//
//		@Override
//		public SourceFile getSourceFile() {
//			SourceFile result = DelegatedElements.sourceFileOf(KotlinNativeLoader.class)
//				.with("package", javaPackage.getName())
//				.getSourceFile();
//			return new SourceFile("kotlin/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
//		}
//
//		@Override
//		public String getSourceSetName() {
//			return "test";
//		}
//
//		public KotlinGreeterJUnitTest(JavaPackage javaPackage) {
//			this.javaPackage = javaPackage;
//		}
//	}
//}
