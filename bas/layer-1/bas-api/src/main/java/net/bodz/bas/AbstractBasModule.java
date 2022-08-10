package net.bodz.bas;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;
import net.bodz.bas.t.project.JazzProjects;

public abstract class AbstractBasModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzProjects.INSTANCE.getProject(JazzBasProject.class);
    }

}
