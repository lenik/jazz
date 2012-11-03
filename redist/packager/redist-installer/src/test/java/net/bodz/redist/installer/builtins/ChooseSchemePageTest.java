package net.bodz.redist.installer.builtins;

import org.junit.Test;

import net.bodz.bas.gui.dialog.ConsoleDialog;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.Session;
import net.bodz.redist.installer.TestProject;
import net.bodz.swt.c3.test.PageTester;

public class ChooseSchemePageTest {

    @Test
    public void test1()
            throws Exception {
        final TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleDialog.stdout, ConsoleLogger.getInstance());
        PageTester app = new PageTester(new ChooseSchemePage(project, session));
        app.run();
    }

}
