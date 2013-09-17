package net.bodz.pkg.installer.builtins;

import org.junit.Test;

import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.Session;
import net.bodz.pkg.installer.TestProject;
import net.bodz.swt.test.PageTester;

public class CustomPageTest {

    @Test
    public void test()
            throws Exception {
        TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleDialogs.stdout, ConsoleLogger.getInstance());
        PageTester app = new PageTester(new CustomPage(project, session));
        app.run();
        session.dump(Stdio.cout);
    }

}
