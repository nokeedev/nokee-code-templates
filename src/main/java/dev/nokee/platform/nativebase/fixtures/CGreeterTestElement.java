package dev.nokee.platform.nativebase.fixtures;


import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeSourceElement;

@AutoElement(className = "CGreeterTest")
public abstract class CGreeterTestElement extends NativeSourceElement {
	@ElementFileTree("templates-c-greeter/c-greeter-test/src/main/headers")
	public abstract SourceElement getHeaders();

	@ElementFileTree("templates-c-greeter/c-greeter-test/src/main/c")
	public abstract SourceElement getSources();
}
