package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;

@SourceProject("templates-cpp-greeter/cpp-greeter")
public final class CppGreeter extends NativeLibraryElement.FromResource {
	public NativeLibraryElement withOptionalFeature() {
		return withSources(new WithOptionalFeatureSource());
	}

	@SourceProject("templates-cpp-greeter/cpp-greeter-with-optional-feature")
	private static final class WithOptionalFeatureSource extends FromResource {}
}
