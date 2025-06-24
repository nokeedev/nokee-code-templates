package dev.nokee.platform.nativebase.fixtures;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;
import dev.nokee.elements.nativebase.NativeSourceElement;
import dev.nokee.platform.jni.fixtures.CGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

public final class CGreeterLib extends ProjectElement implements GreeterImplementationAwareSourceElement {
	@Override
	public NativeElement getMainElement() {
		return NativeSourceElement.ofElements(getGreeter(), getElementUsingGreeter());
	}

	@Override
	public NativeElement getElementUsingGreeter() {
		return new CGreeterLib_CGreetUsingGreeterElement();
	}

	@Override
	public NativeElement getGreeter() {
		return new CGreeter();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject() {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), new CGreeter());
	}

	public ProjectElement withGenericTestSuite() {
		return withTest(new CGreeterTest());
	}

	@AutoElement
	static abstract class CGreetUsingGreeter extends NativeLibraryElement {
		@ElementFileTree("templates-c-greeter/c-greeter-lib/src/main/public")
		public abstract SourceElement getPublicHeaders();

		@ElementFileTree("templates-c-greeter/c-greeter-lib/src/main/c")
		public abstract SourceElement getSources();
	}
}
