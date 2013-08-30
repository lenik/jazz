package net.bodz.bas;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;

public abstract class AbstractBasModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzBasProject.getInstance();
    }

}
