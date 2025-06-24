package dev.nokee.platform.nativebase.fixtures;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.SourceFile;
import dev.nokee.elements.core.SourceFileElement;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeSourceElement;
import dev.nokee.platform.jni.fixtures.CGreeter;
import dev.nokee.platform.jni.fixtures.elements.GreeterImplementationAwareSourceElement;

public final class CGreeterApp extends ProjectElement implements GreeterImplementationAwareSourceElement {
	@Override
	public NativeElement getMainElement() {
		return NativeSourceElement.ofElements(getGreeter(), NativeSourceElement.ofSources(getElementUsingGreeter()));
	}

	@Override
	public SourceElement getElementUsingGreeter() {
		return new CGreeterApp_CMainUsesGreeterElement();
	}

	@Override
	public NativeElement getGreeter() {
		return new CGreeter().asImplementation();
	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject() {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), new CGreeter());
	}

	public ProjectElement withGenericTestSuite() {
		return withTest(new CGreeterTest());
	}

	@AutoElement
	static abstract class CMainUsesGreeter extends SourceFileElement {
		@ElementFileTree("templates-c-greeter/c-greeter-app/src/main/c")
		public abstract SourceFile getSourceFile();
	}
}
