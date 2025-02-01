package dev.nokee.platform;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.SourceFile;

public class ResourceElement<T extends SourceElement> {
	public ResourceElement<T> with(String propName, String value) {
		throw new UnsupportedOperationException();
	}

	public SourceFile getSourceFile() {
		throw new UnsupportedOperationException();
	}
}
