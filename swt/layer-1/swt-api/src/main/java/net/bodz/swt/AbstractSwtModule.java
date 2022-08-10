package net.bodz.swt;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;
import net.bodz.bas.t.project.JazzProjects;

public class AbstractSwtModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzProjects.INSTANCE.getProject(JazzSwtProject.class);
    }

}
