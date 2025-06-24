package dev.nokee.platform.jni.fixtures;

import dev.nokee.elements.AutoElement;
import dev.nokee.elements.ElementFileTree;
import dev.nokee.elements.core.*;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeSourceElement;
import dev.nokee.platform.jni.fixtures.elements.*;

import java.util.function.UnaryOperator;

import static dev.nokee.elements.core.JavaPackage.ofPackage;
import static dev.nokee.elements.core.SourceElement.ofElements;
import static dev.nokee.platform.jni.fixtures.elements.JavaUtils.replace;

public final class JavaJniCGreeterLib extends ProjectElement implements GreeterImplementationAwareSourceElement, JniLibraryElement {
	private final CGreeterJniBindingElement nativeBindings;
	private final SourceElement jvmBindings;
	private final SourceElement jvmImplementation;
	private final CGreeterElement nativeImplementation;

	@Override
	public SourceElement getJvmSources() {
		return ofElements(jvmBindings, jvmImplementation);
	}

	@Override
	public NativeElement getNativeSources() {
		return NativeSourceElement.ofElements(NativeSourceElement.ofSources(nativeBindings), nativeImplementation.asImplementation());
	}

	@Override
	public Element getMainElement() {
		return new Element() {
			@Override
			public void accept(Visitor visitor) {
				getJvmSources().accept(visitor);
				getNativeSources().accept(visitor);
			}
		};
	}

	@Override
	public ProjectElement withJUnitTest() {
		return withTest(new JavaGreeterJUnitTest());
	}

	public JavaJniCGreeterLib(String projectName) {
		this(ofPackage("com.example.greeter"), projectName);
	}

	private JavaJniCGreeterLib(JavaPackage javaPackage, String sharedLibraryBaseName) {
		this(new JavaNativeGreeter().withPackage(javaPackage).withSharedLibraryBaseName(sharedLibraryBaseName).withResourcePath(""), new CGreeterJniBinding().withPackage(javaPackage), new JavaNativeLoader().withPackage(javaPackage), new CGreeter());
	}

	private JavaJniCGreeterLib(JavaNativeGreeterElement jvmBindings, CGreeterJniBindingElement nativeBindings, JavaNativeLoaderElement jvmImplementation, CGreeterElement nativeImplementation) {
		this.jvmBindings = jvmBindings;
		this.nativeBindings = nativeBindings;
		this.jvmImplementation = jvmImplementation;
		this.nativeImplementation = nativeImplementation;
	}

	@Override
	public SourceElement getElementUsingGreeter() {
		return ofElements(jvmBindings, jvmImplementation, nativeBindings);
	}

	@Override
	public NativeElement getGreeter() {
		return nativeImplementation;
	}

	public SourceElement withoutNativeImplementation() {
		return ofElements(getJvmSources(), nativeBindings);
	}

	// FIXME
//	public SourceElement withOptionalFeature() {
//		return ofElements(getJvmSources(),
//			ofElements(nativeBindings, nativeImplementation.withOptionalFeature()));
//	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject() {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), nativeImplementation);
	}

	@AutoElement(className = "CGreeterJniBinding")
	static abstract class CGreeterJniBindingElement extends JniBindingElement {
		private final JavaPackage javaPackage;

		public CGreeterJniBindingElement() {
			this(JavaPackage.ofPackage("com.example.greeter"));
		}

		public CGreeterJniBindingElement(JavaPackage javaPackage) {
			this.javaPackage = javaPackage;
		}

		@ElementFileTree(value = "templates-jni-greeter/jni-c-greeter/src/main/c", includes = {"greeter.c"})
		public abstract SourceFile getSourceFile();

		public CGreeterJniBindingElement withPackage(JavaPackage javaPackage) {
			return new CGreeterJniBindingElement(javaPackage) {
				@Override
				public SourceFile getSourceFile() {
					SourceFile sourceFile = CGreeterJniBindingElement.this.getSourceFile();
					return sourceFile
						.withContent(jniHeader(javaPackage))
						.withContent(methodName(javaPackage));
				}
			};
		}

		private static UnaryOperator<String> methodName(JavaPackage javaPackage) {
			return replace("\\s+(Java_com_example_greeter_Greeter_sayHello)\\(", javaPackage.jniMethodName("Greeter", "sayHello"));
		}

		private static UnaryOperator<String> jniHeader(JavaPackage javaPackage) {
			return replace("^#include\\s+\"(com_example_greeter_Greeter.h)\"$", javaPackage.jniHeader("Greeter"));
		}

		@Override
		public SourceFileElement getJniGeneratedHeaderFile() {
			return new GreeterJniHeader().withPackage(javaPackage);
		}
	}
}
