package dev.nokee.platform;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.SourceFile;
import dev.gradleplugins.fixtures.sources.SwiftSourceElement;

import java.util.List;

public class DelegatedSwiftSourceElement extends SwiftSourceElement {
	private final SourceElement delegate;

	public DelegatedSwiftSourceElement() {
		this.delegate = Elements.sourceOf(getClass());
	}

	public DelegatedSwiftSourceElement(SourceElement delegate) {
		this.delegate = delegate;
	}

	@Override
	public List<SourceFile> getFiles() {
		return delegate.getFiles();
	}

	@Override
	public String getSourceSetName() {
		return delegate.getSourceSetName();
	}
}
