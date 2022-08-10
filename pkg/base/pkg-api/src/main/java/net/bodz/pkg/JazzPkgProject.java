package net.bodz.pkg;

import net.bodz.bas.JazzBasProject;
import net.bodz.bas.meta.autowire.ProjectDependencies;
import net.bodz.bas.meta.autowire.ProjectName;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@ProjectName(JazzPkgProject.NAME)
@ProjectDependencies(JazzBasProject.class)
@MainVersion({ 1, 0 })
public class JazzPkgProject
        extends AbstractJazzProject {

    public static final String NAME = "pkg";

}
