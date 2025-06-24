package dev.nokee.platform.jni.fixtures.elements;

import dev.gradleplugins.fixtures.sources.ApplicationElement;
import dev.nokee.elements.core.Element;

// TODO: Not sure if it should extends from ApplicationElement
public interface ApplicationWithLibraryElement extends ApplicationElement {
	Element getLibrary();
	Element getApplication();
}
