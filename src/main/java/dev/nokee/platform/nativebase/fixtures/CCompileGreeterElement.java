package dev.nokee.platform.nativebase.fixtures;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.SourceFile;

import java.util.List;

@AutoElement(className = "CCompileGreeter")
public abstract class CCompileGreeterElement extends SourceElement {
	@ElementFileTree("templates-c-greeter/c-compile-greeter/src/main/c")
	public abstract List<SourceFile> getFiles();
}
