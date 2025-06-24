package dev.nokee.platform.nativebase.fixtures;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;
import dev.nokee.elements.nativebase.NativeSourceElement;
import dev.nokee.platform.jni.fixtures.elements.CppGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

public final class CppGreeterLib extends ProjectElement implements GreeterImplementationAwareSourceElement {
	@Override
	public NativeElement getMainElement() {
		return NativeSourceElement.ofElements(getGreeter(), getElementUsingGreeter());
	}

	@Override
	public NativeElement getElementUsingGreeter() {
		return new CppGreeterLib_CppGreetUsingGreeterElement();
	}

	@Override
	public NativeElement getGreeter() {
		return new CppGreeter();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject() {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), new CppGreeter());
	}

	public ProjectElement withGenericTestSuite() {
		return withTest(new CppGreeterTest());
	}

	@AutoElement
	static abstract class CppGreetUsingGreeter extends NativeLibraryElement {
		@ElementFileTree("templates-cpp-greeter/cpp-greeter-lib/src/main/public")
		public abstract SourceElement getPublicHeaders();

		@ElementFileTree("templates-cpp-greeter/cpp-greeter-lib/src/main/cpp")
		public abstract SourceElement getSources();
	}
}
