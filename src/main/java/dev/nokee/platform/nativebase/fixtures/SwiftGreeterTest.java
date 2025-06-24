//package dev.nokee.platform.nativebase.fixtures;
//
//import dev.gradleplugins.fixtures.sources.SwiftSourceElement;
//import dev.gradleplugins.fixtures.sources.annotations.SourceFileProperty;
//import dev.gradleplugins.fixtures.sources.annotations.SourceProject;
//import dev.gradleplugins.fixtures.sources.DelegatedElements;
//
//@SourceProject(value = "templates-swift-greeter/swift-greeter-test", includes = {"src/main/swift/greeter_test.swift"}, properties = {
//	@SourceFileProperty(regex = "^import (SwiftGreeter)$", name = "testedModuleName")
//})
//public final class SwiftGreeterTest extends SwiftSourceElement.FromResource {
//	public SwiftGreeterTest(String testedModuleName) {
//		super(fromResource(SwiftGreeterTest.class)/*.with("testedModuleName", testedModuleName)*/);
//	}
//
//	@Override
//	public String getSourceSetName() {
//		return "test";
//	}
//}
