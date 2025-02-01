package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedSourceElement;

@SourceProject("templates-c-greeter/c-greeter-test")
public final class CGreeterTest extends DelegatedSourceElement {
	@Override
	public String getSourceSetName() {
		return "test";
	}
}
