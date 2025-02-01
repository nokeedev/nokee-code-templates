package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedSourceElement;

@SourceProject("templates-objc-greeter/objc-greeter-test")
public final class ObjectiveCGreeterTest extends DelegatedSourceElement {
	@Override
	public String getSourceSetName() {
		return "test";
	}
}
