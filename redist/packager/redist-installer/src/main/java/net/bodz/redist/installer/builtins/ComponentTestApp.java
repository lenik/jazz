package net.bodz.redist.installer.builtins;

import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.redist.installer.ConsoleExecutor;
import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.SessionException;

public class ComponentTestApp
        implements II18nCapable {

    protected final CTAProject project;

    /**
     * Test Section
     * 
     * Components under test should put them here.
     */
    protected final Section section;

    public ComponentTestApp() {
        project = new CTAProject();
        section = new DefaultSection("test");
        project.add(section);
    }

    ConsoleExecutor buildExecutor() {
        ConsoleExecutor executor = new ConsoleExecutor(project, ConsoleLogger.getInstance());
        ISession session = executor.getSession();
        session.addResFolder(0, new PojfFile(TestConfig.outDir/* , true */));
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
