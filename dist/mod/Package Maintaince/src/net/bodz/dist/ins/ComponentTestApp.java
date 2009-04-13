package net.bodz.dist.ins;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.dist.ins.builtins.CLISession;
import net.bodz.dist.ins.builtins.SimpleProject;
import net.bodz.dist.nls.PackNLS;

public class ComponentTestApp {

    static class CTAProject extends SimpleProject {

        public CTAProject() {
            super(ComponentTestApp.class);
            setDoc(PackNLS.getString("ComponentTestApp.doc")); //$NON-NLS-1$
        }

    }

    public CTAProject project;
    public _Session   session;

    public ComponentTestApp() {
        project = new CTAProject();
        session = new CLISession(project);

        File tmpdir = Files.getTmpDir();
        session.setBaseDir(TestProject.PROGRAMS, new File(tmpdir, "testprog")); //$NON-NLS-1$
    }

}
