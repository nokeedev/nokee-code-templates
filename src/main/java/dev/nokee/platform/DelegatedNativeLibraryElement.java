package dev.nokee.platform;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.SourceElement;

import static dev.nokee.platform.Elements.nativeFiles;

public class DelegatedNativeLibraryElement extends NativeLibraryElement {
	private final NativeLibraryElement delegate;

	public DelegatedNativeLibraryElement() {
		this.delegate = (NativeLibraryElement) Elements.sourceOf(getClass()).as(nativeFiles());
	}

	@Override
	public SourceElement getPublicHeaders() {
		return delegate.getPublicHeaders();
	}

	@Override
	public SourceElement getPrivateHeaders() {
		return delegate.getPrivateHeaders();
	}

	@Override
	public SourceElement getSources() {
		return delegate.getSources();
	}

	public NativeLibraryElement withSources(SourceElement sources) {
		return new NativeLibraryElement() {
			@Override
			public SourceElement getSources() {
				return sources;
			}

			@Override
			public SourceElement getPublicHeaders() {
				return delegate.getPublicHeaders();
			}

			@Override
			public SourceElement getPrivateHeaders() {
				return delegate.getPrivateHeaders();
			}

			@Override
			public String getSourceSetName() {
				return delegate.getSourceSetName();
			}
		};
	}

	@Override
	public String getSourceSetName() {
		return delegate.getSourceSetName();
	}
}
