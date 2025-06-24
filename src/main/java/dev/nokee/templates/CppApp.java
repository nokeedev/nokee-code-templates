package dev.nokee.templates;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;
import dev.nokee.elements.nativebase.NativeSourceElement;

// TODO: Should probably be ProjectElement?
public class CppApp extends NativeSourceElement {
	private final NativeSourceElement mainElement;
	private final NativeElement appElements;

	public CppApp() {
		this(new CppApp_CppMainElement());
	}

	private CppApp(NativeSourceElement mainElement) {
		this.mainElement = mainElement;
		appElements = ofElements(mainElement, getList().asImplementation(), getMessage().asImplementation(), getUtilities().asImplementation());
	}

	public NativeElement useLogger() {
		return ofElements(new CppApp_CppMainUsesLoggerElement(), getList().asImplementation(), getMessage().asImplementation(), getUtilities().asImplementation());
	}

	public CppMessage getMessage() {
		return new CppMessage();
	}

	public CppUtilities getUtilities() {
		return new CppUtilities();
	}

	public CppList getList() {
		return new CppList();
	}

	public NativeLibraryElement getLibs() {
		return ofElements(getMessage(), getList(), getUtilities());
	}

	@Override
	public SourceElement getHeaders() {
		return appElements.getHeaders();
	}

	public SourceElement getSources() {
		return appElements.getSources();
	}

	@Override
	public void accept(Visitor visitor) {
		appElements.accept(visitor);
	}

	public NativeSourceElement withoutImplementation() {
		return mainElement;
	}

	@AutoElement
	static abstract class CppMain extends NativeSourceElement {
		@ElementFileTree("templates-cpp-list/cpp-app/src/main/cpp")
		public abstract SourceElement getSources();
	}

	@AutoElement
	static abstract class CppMainUsesLogger extends NativeSourceElement {
		@ElementFileTree("templates-cpp-list/cpp-app-uses-logger/src/main/cpp")
		public abstract SourceElement getSources();
	}
}
