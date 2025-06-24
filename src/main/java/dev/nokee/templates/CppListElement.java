package dev.nokee.templates;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;
import dev.nokee.elements.nativebase.NativeSourceElement;

import java.util.stream.Collectors;

@AutoElement(className = "CppList")
public abstract class CppListElement extends NativeLibraryElement {
	@ElementFileTree("templates-cpp-list/cpp-list/src/main/public")
	public abstract SourceElement getPublicHeaders();

	@ElementFileTree("templates-cpp-list/cpp-list/src/main/headers")
	public abstract SourceElement getPrivateHeaders();

	@ElementFileTree("templates-cpp-list/cpp-list/src/main/cpp")
	public abstract SourceElement getSources();

	public NativeLibraryElement asCppTemplate() {
		return new CppListElement_CppTemplateElement();
	}

	public NativeLibraryElement withCApi() {
		return NativeSourceElement.ofElements(this, new CppListElement_CListAdapterElement());
	}

	public NativeLibraryElement withDllExport() {
		final String moduleName = "list";
		final String macroName = moduleName.toUpperCase() + "_API";
		final String exportMacroName = moduleName.toUpperCase() + "_MODULE_EXPORT";
		final String macroDef = "#define " + macroName;
		return new NativeLibraryElement() {
			@Override
			public SourceElement getPublicHeaders() {
				return SourceElement.ofFiles(CppListElement.this.getPublicHeaders().getFiles().stream().map(it -> it.withContent(c -> c.replace(macroDef, "#ifdef _WIN32\n#  ifdef " + exportMacroName + "\n#    define " + macroName + " __declspec(dllexport)\n#  else\n#    define " + macroName + " __declspec(dllimport)\n#  endif\n#else\n#  define " + macroName + "\n#endif"))).collect(Collectors.toList()));
			}

			@Override
			public SourceElement getPrivateHeaders() {
				return CppListElement.this.getPrivateHeaders();
			}

			@Override
			public SourceElement getSources() {
				return CppListElement.this.getSources();
			}
		};
	}

	@AutoElement
	static abstract class CListAdapter extends NativeLibraryElement {
		@ElementFileTree("templates-cpp-list/cpp-list-with-c-api/src/main/public")
		public abstract SourceElement getPublicHeaders();

		@ElementFileTree("templates-cpp-list/cpp-list-with-c-api/src/main/cpp")
		public abstract SourceElement getSources();
	}

	@AutoElement
	static abstract class CppTemplate extends NativeLibraryElement {
		@ElementFileTree("templates-cpp-list/cpp-list-with-cpp-template/src/main/public")
		public abstract SourceElement getPublicHeaders();

		public SourceElement getSources() {
			return SourceElement.empty();
		}
	}
}
