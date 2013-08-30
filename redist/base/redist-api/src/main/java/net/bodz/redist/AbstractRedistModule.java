package net.bodz.redist;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;

public class AbstractRedistModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzRedistProject.getInstance();
    }

}
