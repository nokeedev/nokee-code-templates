package dev.nokee.templates.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import javax.inject.Inject;

/*private*/ abstract /*final*/ class CodeTemplatesPlugin implements Plugin<Project> {
	@Inject
	public CodeTemplatesPlugin() {}

	@Override
	public void apply(Project project) {

	}
}
