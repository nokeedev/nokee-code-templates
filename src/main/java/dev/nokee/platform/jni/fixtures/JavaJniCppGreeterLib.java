package dev.nokee.platform.jni.fixtures;

import dev.nokee.elements.core.*;
import dev.nokee.elements.nativebase.NativeElement;
import dev.nokee.elements.nativebase.NativeSourceElement;
import dev.nokee.platform.jni.fixtures.elements.*;

import java.util.Arrays;
import java.util.List;

import static dev.nokee.elements.core.JavaPackage.ofPackage;
import static dev.nokee.elements.core.SourceElement.ofElements;

public final class JavaJniCppGreeterLib extends ProjectElement implements GreeterImplementationAwareSourceElement, JniLibraryElement {
	private final CppGreeterJniBindingElement nativeBindings;
	private final JavaNativeGreeterElement jvmBindings;
	private final SourceElement jvmImplementation;
	private final CppGreeterElement nativeImplementation;
	private /*final*/ String projectName;
	private /*final*/ String resourcePath;

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

	public JavaJniCppGreeterLib(String projectName) {
		this(projectName, "");
	}

	public JavaJniCppGreeterLib(String projectName, String resourcePath) {
		this(ofPackage("com.example.greeter"), projectName, resourcePath);
		this.resourcePath = resourcePath;
		this.projectName = projectName;
	}

	private JavaJniCppGreeterLib(JavaPackage javaPackage, String sharedLibraryBaseName, String resourcePath) {
		this(new JavaNativeGreeter().withPackage(javaPackage).withSharedLibraryBaseName(sharedLibraryBaseName).withResourcePath(resourcePath), new CppGreeterJniBinding().withPackage(javaPackage), new JavaNativeLoader().withPackage(javaPackage), new CppGreeter());
	}

	private JavaJniCppGreeterLib(JavaNativeGreeterElement jvmBindings, CppGreeterJniBindingElement nativeBindings, JavaNativeLoaderElement jvmImplementation, CppGreeterElement nativeImplementation) {
		this.jvmBindings = jvmBindings;
		this.nativeBindings = nativeBindings;
		this.jvmImplementation = jvmImplementation;
		this.nativeImplementation = nativeImplementation;
	}

	@Override
	public SourceElement getElementUsingGreeter() {
		return ofElements(jvmBindings, nativeBindings, jvmImplementation);
	}

	@Override
	public NativeElement getGreeter() {
		return nativeImplementation;
	}

	public JavaJniCppGreeterLib withProjectName(String projectName) {
		return new JavaJniCppGreeterLib(projectName, resourcePath);
	}

	public JavaJniCppGreeterLib withResourcePath(String resourcePath) {
		assert resourcePath.endsWith("/");
		return new JavaJniCppGreeterLib(projectName, resourcePath);
	}

	public SourceElement withoutNativeImplementation() {
		return ofElements(getJvmSources(), nativeBindings);
	}

	public Element withoutJvmImplementation() {
		return NativeSourceElement.ofElements(NativeSourceElement.ofSources(jvmBindings), NativeSourceElement.ofSources(nativeBindings), nativeImplementation);
	}

	public WorkspaceElement withImplementationAsSubprojects() {
		return new WorkspaceElement() {
			@Override
			public List<ProjectElement> getProjects() {
				return Arrays.asList(
					new JavaJniGreeterProject() {
						@Override
						public Element getMainElement() {
							return jvmBindings.withSharedLibraryBaseName("cpp-jni-greeter");
						}
					},
					new CppJniGreeterProject() {

						@Override
						public Element getMainElement() {
							return nativeBindings.withJniGeneratedHeader();
						}
					},
					new JavaLoaderProject() {
						@Override
						public Element getMainElement() {
							return jvmImplementation;
						}
					},
					new CppGreeterProject() {
						@Override
						public Element getMainElement() {
							return nativeImplementation;
						}
					}
				);
			}
		};
	}

	// "java-jni-greeter"
	public static abstract class JavaJniGreeterProject extends ProjectElement {}

	// "cpp-jni-greeter"
	public static abstract class CppJniGreeterProject extends ProjectElement {}

	// "java-loader"
	public static abstract class JavaLoaderProject extends ProjectElement {}

	// "cpp-greeter"
	public static abstract class CppGreeterProject extends ProjectElement {}

	// FIXME
//	public SourceElement withOptionalFeature() {
//		return ofElements(getJvmSources(),
//			ofElements(nativeBindings, nativeImplementation.withOptionalFeature()));
//	}

	@Override
	public ImplementationAsSubprojectElement withImplementationAsSubproject() {
		return new ImplementationAsSubprojectElement(getElementUsingGreeter(), nativeImplementation);
	}

	// FIXME
//	// withoutNativeImplementation().withJUnitTest()
//	public SourceElement asSample() {
//		return ofElements(withoutNativeImplementation(), new JavaGreeterJUnitTest());
//	}
}

