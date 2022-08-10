package net.bodz.swt;

import net.bodz.bas.JazzBasProject;
import net.bodz.bas.meta.autowire.ProjectDependencies;
import net.bodz.bas.meta.autowire.ProjectName;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@ProjectName(JazzSwtProject.NAME)
@ProjectDependencies(JazzBasProject.class)
@MainVersion({ 1, 2 })
public class JazzSwtProject
        extends AbstractJazzProject {

    public static final String NAME = "swt";

}
