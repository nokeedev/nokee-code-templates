package dev.nokee.templates;

import dev.gradleplugins.fixtures.sources.NativeLibraryElement;
import dev.gradleplugins.fixtures.sources.SourceElement;
import dev.gradleplugins.fixtures.sources.annotations.SourceProject;

public final class CppMessageLib extends NativeLibraryElement {
	private final NativeLibraryElement messageElement;

	public CppMessageLib() {
		this(new CppMessage());
	}

	private CppMessageLib(NativeLibraryElement messageElement) {
		this.messageElement = messageElement;
	}

	public NativeLibraryElement fromResources() {
		return new CppMessageFromResources();
	}

	@Override
	public SourceElement getSources() {
		return messageElement.getSources();
	}

	@Override
	public SourceElement getPrivateHeaders() {
		return messageElement.getPrivateHeaders();
	}

	@Override
	public SourceElement getPublicHeaders() {
		return messageElement.getPublicHeaders();
	}

	@SourceProject("templates-cpp-list/cpp-message")
	private static final class CppMessage extends FromResource {
	}

	@SourceProject("templates-cpp-list/cpp-message-from-resources")
	private static final class CppMessageFromResources extends FromResource {}
}
