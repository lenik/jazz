package net.bodz.bas;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.t.project.AbstractJazzProject;

@MainVersion({ 2, 0 })
public class JazzBasProject
        extends AbstractJazzProject {

    private static JazzBasProject instance = new JazzBasProject();

    public static JazzBasProject getInstance() {
        return instance;
    }

}
