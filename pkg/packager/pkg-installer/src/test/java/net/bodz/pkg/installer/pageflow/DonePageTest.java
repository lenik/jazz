package net.bodz.pkg.installer.pageflow;

import org.junit.Test;

import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.pkg.installer.IProject;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.Session;
import net.bodz.pkg.installer.TestProject;
import net.bodz.pkg.installer.pageflow.DonePage;
import net.bodz.swt.test.PageTester;

public class DonePageTest {

    @Test
    public void test()
            throws Exception {
        final IProject project = new TestProject();
        final ISession session = new Session(project, ConsoleDialogs.stdout, ConsoleLogger.getInstance());
        PageTester app = new PageTester(new DonePage(session));
        app.run();
    }

}
