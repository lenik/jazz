package net.bodz.lily;

import net.bodz.bas.t.project.AbstractJazzModule;
import net.bodz.bas.t.project.IJazzProject;

public abstract class AbstractLilyModule
        extends AbstractJazzModule {

    @Override
    public IJazzProject getProject() {
        return JazzLilyProject.getInstance();
    }

}
