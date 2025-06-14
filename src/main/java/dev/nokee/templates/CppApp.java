package dev.nokee.templates;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.NativeSourceElement;
import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;

public final class CppApp extends NativeSourceElement {
	private final SourceElement mainElement;

	public CppApp() {
		this(new CppMain());
	}

	private CppApp(SourceElement mainElement) {
		this.mainElement = mainElement;
	}

	public CppMessageLib getMessage() {
		return new CppMessageLib();
	}

	public CppUtilitiesLib getUtilities() {
		return new CppUtilitiesLib();
	}

	public CppListLib getList() {
		return new CppListLib();
	}

	public SourceElement withoutImplementation() {
		return mainElement;
	}

	public SourceElement getLibs() {
		return ofElements(getMessage().asLib(), getUtilities().asLib(), getList().asLib());
	}

	public CppApp useLogger() {
		return new CppApp(new CppMainUsesLogger());
	}

	@Override
	public SourceElement getHeaders() {
		return ofElements(getMessage().getHeaders(), getUtilities().getHeaders(), getList().getHeaders());
	}

	@Override
	public SourceElement getSources() {
		return ofElements(mainElement, getMessage().getSources(), getUtilities().getSources(), getList().getSources());
	}

	@SourceProject("templates-cpp-list/cpp-app")
	private static final class CppMain extends FromResource {}

	@SourceProject("templates-cpp-list/cpp-app-uses-logger")
	private static final class CppMainUsesLogger extends FromResource {}
}
