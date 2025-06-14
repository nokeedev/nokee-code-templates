package dev.nokee.templates;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;

public final class CppLoggerLib {
	@SourceProject("templates-cpp-list/cpp-logger-uses-console")
	public static class CppLoggerUsesConsole extends NativeLibraryElement.FromResource {}

	@SourceProject("templates-cpp-list/cpp-ansi-console-lib")
	public static class CppAnsiConsoleLib extends NativeLibraryElement.FromResource {}

	@SourceProject("templates-cpp-list/cpp-windows-console-lib")
	public static class CppWindowsConsoleLib extends NativeLibraryElement.FromResource {}
}
