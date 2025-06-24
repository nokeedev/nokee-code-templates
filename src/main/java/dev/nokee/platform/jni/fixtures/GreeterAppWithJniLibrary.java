package dev.nokee.platform.jni.fixtures;

import dev.nokee.elements.core.Element;
import dev.nokee.elements.core.ProjectElement;
import dev.nokee.elements.core.SourceElement;
import dev.nokee.elements.core.WorkspaceElement;
import dev.nokee.platform.jni.fixtures.elements.ApplicationWithLibraryElement;
import dev.nokee.platform.jni.fixtures.elements.JavaMainUsesGreeter;
import dev.nokee.platform.jni.fixtures.elements.JavaMainUsesGreeterElement;

import java.util.Arrays;
import java.util.List;

// FIXME(elements)
public final class GreeterAppWithJniLibrary implements ApplicationWithLibraryElement {
	private final JavaJniCppGreeterLib library;
	private final JavaMainUsesGreeterElement application = new JavaMainUsesGreeter();
	private final String resourcePath;
	private final String projectName;

    public GreeterAppWithJniLibrary(String projectName) {
		this(projectName, "");
	}
    public GreeterAppWithJniLibrary(String projectName, String resourcePath) {
		this.projectName = projectName;
		this.resourcePath = resourcePath;
		library = new JavaJniCppGreeterLib(projectName, resourcePath);
	}

	@Override
	public String getExpectedOutput() {
		return application.getExpectedOutput();
	}

	public WorkspaceElement withLibraryAsSubproject(String libraryProjectName) {
		if (resourcePath.isEmpty()) {
			return newLibraryAsSubproject(libraryProjectName, projectName + "/");
		}
		return newLibraryAsSubproject(libraryProjectName, resourcePath);
	}

	// FIXME
	private WorkspaceElement newLibraryAsSubproject(String libraryProjectName, String resourcePath) {
		return new WorkspaceElement() {
			@Override
			public List<ProjectElement> getProjects() {
				return Arrays.asList(ProjectElement.ofMain(library.withResourcePath(resourcePath).withProjectName(libraryProjectName)), ProjectElement.ofMain(application));
			}
		};
//		return new SourceElement() {
//			@Override
//			public List<SourceFile> getFiles() {
//				throw new UnsupportedOperationException();
//			}
//
//			@Override
//			public void writeToProject(Path projectDir) {
//				.writeToProject(projectDir.resolve(libraryProjectName));
//				application.writeToProject(projectDir);
//			}
//
//			SourceElement withResourcePath(String newResourcePath) {
//				return newLibraryAsSubproject(libraryProjectName, newResourcePath);
//			}
//		};
	}

	public GreeterAppWithJniLibrary withResourcePath(String resourcePath) {
		return new GreeterAppWithJniLibrary(projectName, resourcePath);
	}

	@Override
	public Element getLibrary() {
		return library.getMainElement();
	}

	@Override
	public SourceElement getApplication() {
		return application;
	}
}
