package net.bodz.art.installer.builtins;

import net.bodz.art.installer.ISession;
import net.bodz.art.installer.Session;
import net.bodz.art.installer.TestProject;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.swt.c3.test.PageTestApp;

import org.junit.Test;

public class CustomPageTest {

    @Test
    public void test()
            throws Exception {
        TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, ConsoleLogger.getInstance());
        PageTestApp app = new PageTestApp(new CustomPage(project, session));
        app.run();
        session.dump(Stdio.cout);
    }

}
