package net.bodz.dist.ins;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import net.bodz.dist.ins.builtins.CLISession;
import net.bodz.dist.ins.builtins.SimpleProject;
import net.bodz.dist.nls.PackNLS;

public class ComponentTestApp {

    public static final TextMap<Object> env;
    static {
        env = new TreeTextMap<Object>(System.getenv());

    }

    static class CTAProject extends SimpleProject {

        public CTAProject() {
            super(ComponentTestApp.class);
            setDoc(PackNLS.getString("ComponentTestApp.doc")); //$NON-NLS-1$
        }

    }

    static class CTASession extends CLISession {

        public CTASession(IProject project) {
            super(project, env, DEBUG);

            File tmpdir = Files.getTmpDir();
            setBaseDir(TestProject.PROGRAMS, new File(tmpdir, "testprog")); //$NON-NLS-1$
        }

    }

    CTAProject project;
    CTASession session;

    public ComponentTestApp() {
        project = new CTAProject();
        session = new CTASession(project);
    }

}
