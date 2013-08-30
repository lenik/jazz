package net.bodz.mda;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@MainVersion({ 0, 0 })
public class JazzMdaProject
        extends AbstractJazzProject {

    private static JazzMdaProject instance = new JazzMdaProject();

    public static JazzMdaProject getInstance() {
        return instance;
    }

}
