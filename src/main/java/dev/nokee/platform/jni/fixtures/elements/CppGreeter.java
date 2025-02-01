package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedNativeLibraryElement;
import dev.nokee.platform.DelegatedSourceElement;

@SourceProject("templates-cpp-greeter/cpp-greeter")
public final class CppGreeter extends DelegatedNativeLibraryElement {
	public NativeLibraryElement withOptionalFeature() {
		return withSources(new WithOptionalFeatureSource());
	}

	@SourceProject("templates-cpp-greeter/cpp-greeter-with-optional-feature")
	static class WithOptionalFeatureSource extends DelegatedSourceElement {}
}
