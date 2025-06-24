package dev.nokee.templates;


import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.Element;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeLibraryElement;

import java.util.stream.Collectors;

@AutoElement(className = "CppUtilities")
public abstract class CppUtilitiesElement extends NativeLibraryElement {
	@ElementFileTree("templates-cpp-list/cpp-utilities/src/main/public")
	public abstract SourceElement getPublicHeaders();

	@ElementFileTree("templates-cpp-list/cpp-utilities/src/main/cpp")
	public abstract SourceElement getSources();

	public NativeLibraryElement withDllExport() {
		final String moduleName = "utilities";
		final String macroName = moduleName.toUpperCase() + "_API";
		final String exportMacroName = moduleName.toUpperCase() + "_MODULE_EXPORT";
		final String macroDef = "#define " + macroName;
		return new NativeLibraryElement() {
			@Override
			public SourceElement getPublicHeaders() {
				return SourceElement.ofFiles(CppUtilitiesElement.this.getPublicHeaders().getFiles().stream().map(it -> it.withContent(c -> c.replace(macroDef, "#ifdef _WIN32\n#  ifdef " + exportMacroName + "\n#    define " + macroName + " __declspec(dllexport)\n#  else\n#    define " + macroName + " __declspec(dllimport)\n#  endif\n#else\n#  define " + macroName + "\n#endif"))).collect(Collectors.toList()));
			}

			@Override
			public SourceElement getPrivateHeaders() {
				return CppUtilitiesElement.this.getPrivateHeaders();
			}

			@Override
			public SourceElement getSources() {
				return CppUtilitiesElement.this.getSources();
			}
		};
	}
}
