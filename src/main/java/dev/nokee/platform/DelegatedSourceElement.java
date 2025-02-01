package dev.nokee.platform;

import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.SourceFile;

import java.util.List;

public class DelegatedSourceElement extends SourceElement {
	private final SourceElement delegate;

	protected DelegatedSourceElement() {
		this.delegate = Elements.sourceOf(getClass());
	}

	protected DelegatedSourceElement(SourceElement delegate) {
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
