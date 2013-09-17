package net.bodz.pkg;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@MainVersion({ 1, 0 })
public class JazzPkgProject
        extends AbstractJazzProject {

    private static JazzPkgProject instance = new JazzPkgProject();

    public static JazzPkgProject getInstance() {
        return instance;
    }

}
