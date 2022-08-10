package net.bodz.bas;

import net.bodz.bas.meta.autowire.ProjectName;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@ProjectName(JazzBasProject.NAME)
@MainVersion({ 2, 0 })
public class JazzBasProject
        extends AbstractJazzProject {

    public static final String NAME = "bas";

}
