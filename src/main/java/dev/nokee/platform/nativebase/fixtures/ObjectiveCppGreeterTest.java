package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedSourceElement;

@SourceProject("templates-objcpp-greeter/objcpp-greeter-test")
public final class ObjectiveCppGreeterTest extends DelegatedSourceElement {
	@Override
	public String getSourceSetName() {
		return "test";
	}
}
