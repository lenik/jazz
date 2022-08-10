package net.bodz.pkg;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;
import net.bodz.bas.t.project.JazzProjects;

public class AbstractPkgModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzProjects.INSTANCE.getProject(JazzPkgProject.class);
    }

}
