package net.bodz.lily;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@MainVersion({ 1, 0 })
public class JazzLilyProject
        extends AbstractJazzProject {

    private static JazzLilyProject instance = new JazzLilyProject();

    public static JazzLilyProject getInstance() {
        return instance;
    }

}
