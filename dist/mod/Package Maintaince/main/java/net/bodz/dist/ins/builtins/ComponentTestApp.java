package net.bodz.dist.ins.builtins;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.ConsoleExecutor;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.SessionException;
import net.bodz.dist.ins.nls.PackNLS;

public class ComponentTestApp {

    protected final CTAProject project;
    protected final Section    section;

    public ComponentTestApp() {
        project = new CTAProject();
        section = new DefaultSection("test", PackNLS.getString("ComponentTestApp.test.text"), //$NON-NLS-1$ //$NON-NLS-2$
                PackNLS.getString("ComponentTestApp.test.doc")); //$NON-NLS-1$
        project.add(section);
    }

    ConsoleExecutor buildExecutor() {
        ConsoleExecutor executor = new ConsoleExecutor(project, LogTerms.console);
        ISession session = executor.getSession();
        session.addResFolder(0, new FileResFolder(TestConfig.outDir, true));
        return executor;
    }

    public void testPack() throws SessionException {
        buildExecutor().pack();
    }

    public void testInstall() throws SessionException {
        buildExecutor().install();
    }

    public void testUninstall() throws SessionException {
        buildExecutor().uninstall();
    }

}
