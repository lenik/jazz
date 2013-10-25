package net.bodz.pkg.sis.c;

import net.bodz.bas.c.action.IProgressMonitor;
import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.rtx.Options;
import net.bodz.pkg.sis.ConsoleProgressMonitor;
import net.bodz.pkg.sis.test.SisComponentTestProject;
import net.bodz.pkg.sis.test.TestConfig;

public class SisComponentTester
        implements II18nCapable {

    protected final SisComponentTestProject project;

    /**
     * Test Section
     * 
     * Components under test should put them here.
     */
    protected final SisSection section;

    IProgressMonitor monitor;
    IUserDialogs dialogs;
    Options options;

    public SisComponentTester() {
        project = new SisComponentTestProject();
        section = new SisSection(project, "test");

        project.setArchive(TestConfig.outDir);

        monitor = new ConsoleProgressMonitor();
        dialogs = ConsoleDialogs.stdout;
        options = new Options();
        options.addOption(IUserDialogs.class, dialogs);
    }

    public void testPack() {
        project.pack(monitor, options);
    }

    public void testInstall() {
        project.install(monitor, options);
    }

    public void testRemove() {
        project.remove(monitor, options);
    }

}
