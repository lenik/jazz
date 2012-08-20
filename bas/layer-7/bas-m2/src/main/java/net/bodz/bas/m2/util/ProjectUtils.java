package net.bodz.bas.m2.util;

import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluator;

import net.bodz.bas.err.UnexpectedException;

public class ProjectUtils {

    public static MavenProject getProject(ExpressionEvaluator expressionEvaluator) {
        MavenProject project;
        try {
            project = (MavenProject) expressionEvaluator.evaluate("${project}");
        } catch (ExpressionEvaluationException e) {
            throw new UnexpectedException("Failed to evaluate expression: ${project}", e);
        }
        return project;
    }

}
