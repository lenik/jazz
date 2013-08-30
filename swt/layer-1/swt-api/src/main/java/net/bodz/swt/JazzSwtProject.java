package net.bodz.swt;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@MainVersion({ 1, 2 })
public class JazzSwtProject
        extends AbstractJazzProject {

    private static JazzSwtProject instance = new JazzSwtProject();

    public static JazzSwtProject getInstance() {
        return instance;
    }

}
