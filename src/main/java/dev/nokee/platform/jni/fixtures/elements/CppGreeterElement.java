package dev.nokee.platform.jni.fixtures.elements;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.nativebase.NativeLibraryElement;

import java.util.List;

@AutoElement(className = "CppGreeter")
public abstract class CppGreeterElement extends NativeLibraryElement {
	@ElementFileTree(value = "templates-cpp-greeter/cpp-greeter/src/main/public")
	public abstract SourceElement getPublicHeaders();

	@ElementFileTree(value = "templates-cpp-greeter/cpp-greeter/src/main/cpp")
	public abstract SourceElement getSources();

	public NativeLibraryElement withOptionalFeature() {
		return withSources(new CppGreeterElement_WithOptionalFeatureElement());
	}

	@AutoElement
	static abstract class WithOptionalFeature extends SourceElement {
		@ElementFileTree("templates-cpp-greeter/cpp-greeter-with-optional-feature/src/main/cpp")
		public abstract List<SourceFile> getFiles();
	}
}
