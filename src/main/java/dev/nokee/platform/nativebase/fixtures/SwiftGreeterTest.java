package dev.nokee.platform.nativebase.fixtures;

import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
import dev.nokee.platform.DelegatedSwiftSourceElement;
import dev.nokee.platform.Elements;

@SourceProject(value = "templates-swift-greeter/swift-greeter-test", includes = {"src/main/swift/greeter_test.swift"}, properties = {
	@SourceFileProperty(regex = "^import (SwiftGreeter)$", name = "testedModuleName")
})
public final class SwiftGreeterTest extends DelegatedSwiftSourceElement {
	public SwiftGreeterTest(String testedModuleName) {
		super(Elements.sourceOf(SwiftGreeterTest.class)/*.with("testedModuleName", testedModuleName)*/);
	}

	@Override
	public String getSourceSetName() {
		return "test";
	}
}
