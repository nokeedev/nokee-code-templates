//package dev.nokee.platform.jni.fixtures;
//
//import dev.gradleplugins.fixtures.sources.SourceElement;
//import dev.gradleplugins.fixtures.sources.SourceFile;
//import dev.gradleplugins.fixtures.sources.SourceFileElement;
//import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
//import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
//import dev.gradleplugins.fixtures.sources.java.JavaPackage;
//import dev.gradleplugins.fixtures.sources.DelegatedElements;
//import dev.nokee.platform.jni.fixtures.elements.JavaGreeterJUnitTest;
//import dev.nokee.platform.jni.fixtures.elements.JavaNativeLoaderElement;
//import dev.nokee.platform.jni.fixtures.elements.JniLibraryElement;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static dev.gradleplugins.fixtures.sources.java.JavaPackage.ofPackage;
//
//public final class JavaJniObjectiveCNSSavePanelLib extends SourceElement implements JniLibraryElement {
//	private final ObjectiveCNSSavePanelJniBinding nativeBindings;
//	private final SourceElement jvmBindings;
//	private final SourceElement jvmImplementation;
//
//	@Override
//	public SourceElement getJvmSources() {
//		return ofElements(jvmBindings, jvmImplementation);
//	}
//
//	@Override
//	public SourceElement getNativeSources() {
//		return nativeBindings;
//	}
//
//	@Override
//	public SourceElement withJUnitTest() {
//		return ofElements(this, new JavaGreeterJUnitTest());
//	}
//
//	public JavaJniObjectiveCNSSavePanelLib(String projectName) {
//		JavaPackage javaPackage = ofPackage("com.example.cocoa");
//		String sharedLibraryBaseName = projectName;
//		jvmBindings = new JavaNativeNSSavePanel(javaPackage, sharedLibraryBaseName);
//		nativeBindings = new ObjectiveCNSSavePanelJniBinding(javaPackage);
//
//		jvmImplementation = new JavaNativeLoaderElement(javaPackage);
//	}
//
//	@Override
//	public List<SourceFile> getFiles() {
//		return Stream.concat(getJvmSources().getFiles().stream(), getNativeSources().getFiles().stream()).collect(Collectors.toList());
//	}
//
//	@SourceProject(value = "templates-jni-greeter/jni-objc-cocoa", includes = {"src/main/java/com/example/cocoa/NSSavePanel.java"}, properties = {
//		@SourceFileProperty(regex = "^package (com\\.example\\.cocoa);$", name = "package"),
//		@SourceFileProperty(regex = "\"(\\$\\{resourcePath\\}\\$\\{sharedLibraryBaseName\\})\"", name = "libName")
//	})
//	private static class JavaNativeNSSavePanel extends SourceFileElement {
//		private final JavaPackage javaPackage;
//		private final String sharedLibraryBaseName;
//		private final String resourcePath;
//
//		@Override
//		public SourceFile getSourceFile() {
//			SourceFile result = DelegatedElements.sourceFileOf(JavaNativeNSSavePanel.class)
//				.with("package", javaPackage.getName())
//				.with("libName", resourcePath + sharedLibraryBaseName)
//				.getSourceFile();
//			return new SourceFile("java/" + javaPackage.getDirectoryLayout(), result.getName(), result.getContent());
//		}
//
//		public JavaNativeNSSavePanel(JavaPackage javaPackage, String sharedLibraryBaseName) {
//			this(javaPackage, sharedLibraryBaseName, "");
//		}
//
//		public JavaNativeNSSavePanel(JavaPackage javaPackage, String sharedLibraryBaseName, String resourcePath) {
//			this.javaPackage = javaPackage;
//			this.sharedLibraryBaseName = sharedLibraryBaseName;
//			this.resourcePath = resourcePath;
//		}
//
//		public JavaNativeNSSavePanel withSharedLibraryBaseName(String sharedLibraryBaseName) {
//			return new JavaNativeNSSavePanel(javaPackage, sharedLibraryBaseName, resourcePath);
//		}
//
//		public JavaNativeNSSavePanel withResourcePath(String resourcePath) {
//			return new JavaNativeNSSavePanel(javaPackage, sharedLibraryBaseName, resourcePath);
//		}
//	}
//
//	@SourceProject(value = "templates-jni-greeter/jni-objc-cocoa", includes = {"src/main/objc/ns_save_panel.m"}, properties = {
//		@SourceFileProperty(regex = "\\s+(Java_com_example_cocoa_NSSavePanel_saveDialog)\\(", name = "methodName"),
//		@SourceFileProperty(regex = "^#include\\s+\"(com_example_cocoa_NSSavePanel\\.h)\"$", name = "jniHeader")
//	})
//	private static class ObjectiveCNSSavePanelJniBinding extends SourceFileElement {
//		private final JavaPackage javaPackage;
//
//		public ObjectiveCNSSavePanelJniBinding(JavaPackage javaPackage) {
//			this.javaPackage = javaPackage;
//		}
//
//		@Override
//		public SourceFile getSourceFile() {
//			return DelegatedElements.sourceFileOf(ObjectiveCNSSavePanelJniBinding.class)
//				.with("methodName", javaPackage.jniMethodName("NSSavePanel", "saveDialog"))
//				.with("jniHeader", javaPackage.jniHeader("NSSavePanel"))
//				.getSourceFile();
//		}
//	}
//}
