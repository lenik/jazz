package net.bodz.redist.installer;

import org.junit.Test;

import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.log.impl.ConsoleLogger;
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
