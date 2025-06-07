package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;

@SourceProject("templates-c-greeter/c-greeter-test")
public final class CGreeterTest extends SourceElement.FromResource {
	@Override
	public String getSourceSetName() {
		return "test";
	}
}
