package dev.nokee.templates;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.Element;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;

import java.util.stream.Collectors;

@AutoElement(className = "CppMessage")
public abstract class CppMessageElement extends NativeLibraryElement {
	public NativeLibraryElement fromResources() {
		return new CppMessageElement_CppMessageFromResourcesElement();
	}

	@ElementFileTree("templates-cpp-list/cpp-message/src/main/cpp")
	public abstract SourceElement getSources();

	@ElementFileTree("templates-cpp-list/cpp-message/src/main/public")
	public abstract SourceElement getPublicHeaders();

	public NativeLibraryElement withDllExport() {
		final String moduleName = "message";
		final String macroName = moduleName.toUpperCase() + "_API";
		final String exportMacroName = moduleName.toUpperCase() + "_MODULE_EXPORT";
		final String macroDef = "#define " + macroName;
		return new NativeLibraryElement() {
			@Override
			public SourceElement getPublicHeaders() {
				return SourceElement.ofFiles(CppMessageElement.this.getPublicHeaders().getFiles().stream().map(it -> it.withContent(c -> c.replace(macroDef, "#ifdef _WIN32\n#  ifdef " + exportMacroName + "\n#    define " + macroName + " __declspec(dllexport)\n#  else\n#    define " + macroName + " __declspec(dllimport)\n#  endif\n#else\n#  define " + macroName + "\n#endif"))).collect(Collectors.toList()));
			}

			@Override
			public SourceElement getPrivateHeaders() {
				return CppMessageElement.this.getPrivateHeaders();
			}

			@Override
			public SourceElement getSources() {
				return CppMessageElement.this.getSources();
			}
		};
	}

	@AutoElement
	static abstract class CppMessageFromResources extends NativeLibraryElement {
		@ElementFileTree("templates-cpp-list/cpp-message/src/main/public")
		public abstract SourceElement getPublicHeaders();

		@ElementFileTree("templates-cpp-list/cpp-message-from-resources/src/main/headers")
		public abstract SourceElement getPrivateHeaders();

		@ElementFileTree("templates-cpp-list/cpp-message-from-resources/src/main/cpp")
		public abstract SourceElement getSources();

		// TODO: Missing Windows resources
	}
}
