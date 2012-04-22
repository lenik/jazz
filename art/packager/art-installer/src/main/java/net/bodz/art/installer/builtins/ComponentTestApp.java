package net.bodz.art.installer.builtins;

import net.bodz.art.installer.ConsoleExecutor;
import net.bodz.art.installer.ISession;
import net.bodz.art.installer.SessionException;
import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;

public class ComponentTestApp {

    protected final CTAProject project;
    protected final Section section;

    public ComponentTestApp() {
        project = new CTAProject();
        section = new DefaultSection("test", PackNLS.getString("ComponentTestApp.test.text"), //$NON-NLS-1$ //$NON-NLS-2$
                PackNLS.getString("ComponentTestApp.test.doc")); //$NON-NLS-1$
        project.add(section);
    }

    ConsoleExecutor buildExecutor() {
        ConsoleExecutor executor = new ConsoleExecutor(project, LogTerms.console);
        ISession session = executor.getSession();
        session.addResFolder(0, new JavaioFile(TestConfig.outDir/*, true*/));
        return executor;
    }

    public void testPack()
            throws SessionException {
        buildExecutor().pack();
    }

    public void testInstall()
            throws SessionException {
        buildExecutor().install();
    }

    public void testUninstall()
            throws SessionException {
        buildExecutor().uninstall();
    }

}
