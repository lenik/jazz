package net.bodz.mda;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;

public abstract class AbstractMdaModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzMdaProject.getInstance();
    }

}
