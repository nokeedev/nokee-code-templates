package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;

@SourceProject("templates-objc-greeter/objc-greeter-test")
public final class ObjectiveCGreeterTest extends SourceElement.FromResource {
	@Override
	public String getSourceSetName() {
		return "test";
	}
}
