package net.bodz.mda;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;
import net.bodz.bas.t.project.JazzProjects;

public abstract class AbstractMdaModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzProjects.INSTANCE.getProject(JazzMdaProject.class);
    }

}
