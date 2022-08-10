package net.bodz.lily;

import net.bodz.bas.JazzBasProject;
import net.bodz.bas.meta.autowire.ProjectDependencies;
import net.bodz.bas.meta.autowire.ProjectName;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@ProjectName(JazzLilyProject.NAME)
@ProjectDependencies(JazzBasProject.class)
@MainVersion({ 1, 0 })
public class JazzLilyProject
        extends AbstractJazzProject {

    public static final String NAME = "lily";

}
