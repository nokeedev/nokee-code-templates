package dev.nokee.platform.jni.fixtures.elements;

import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeSourceElement;

import java.nio.file.Path;

public interface JniLibraryElement {
	SourceElement getJvmSources();

	NativeElement getNativeSources();

	ProjectElement withJUnitTest();

	// FIXME
//	default SourceElement withResources() {
//		final SourceFileElement newResourceElement = new SourceFileElement() {
//			@Override
//			public SourceFile getSourceFile() {
//				return sourceFile("resources", "foo.txt", "");
//			}
//		};
//		return ofElements(getJvmSources(), newResourceElement, getNativeSources());
//	}
}
