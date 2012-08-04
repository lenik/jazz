package net.bodz.art.installer;

import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.swt.c3.test.PageTestApp;

import org.junit.Test;

public class DonePageTest {

    @Test
    public void test()
            throws Exception {
        final IProject project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, ConsoleLogger.getInstance());
        PageTestApp app = new PageTestApp(new DonePage(session));
        app.run();
    }

}
