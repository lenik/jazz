package net.bodz.redist;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@MainVersion({ 1, 0 })
public class JazzRedistProject
        extends AbstractJazzProject {

    private static JazzRedistProject instance = new JazzRedistProject();

    public static JazzRedistProject getInstance() {
        return instance;
    }

}
