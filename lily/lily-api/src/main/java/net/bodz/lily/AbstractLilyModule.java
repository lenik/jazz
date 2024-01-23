package net.bodz.lily;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;
import net.bodz.bas.t.project.JazzProjects;

public abstract class AbstractLilyModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzProjects.INSTANCE.getProject(JazzLilyProject.class);
    }

}
