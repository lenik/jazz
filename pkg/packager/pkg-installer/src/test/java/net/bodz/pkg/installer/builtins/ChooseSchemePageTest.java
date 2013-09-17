package net.bodz.pkg.installer.builtins;

import org.junit.Test;

import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.Session;
import net.bodz.pkg.installer.TestProject;
import net.bodz.swt.test.PageTester;

public class ChooseSchemePageTest {

    @Test
    public void test1()
            throws Exception {
        final TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleDialogs.stdout, ConsoleLogger.getInstance());
        PageTester app = new PageTester(new ChooseSchemePage(project, session));
        app.run();
    }

}
