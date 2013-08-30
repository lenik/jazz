package net.bodz.swt;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;

public class AbstractSwtModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzSwtProject.getInstance();
    }

}
