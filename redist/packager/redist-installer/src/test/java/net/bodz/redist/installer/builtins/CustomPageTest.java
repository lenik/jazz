package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.Session;
import net.bodz.redist.installer.TestProject;
import net.bodz.bas.gui.ia.ConsoleInteraction;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.bas.sio.Stdio;
import net.bodz.swt.c3.test.PageTestApp;

import org.junit.Test;

public class CustomPageTest {

    @Test
    public void test()
            throws Exception {
        TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleInteraction.stdout, ConsoleLogger.getInstance());
        PageTestApp app = new PageTestApp(new CustomPage(project, session));
        app.run();
        session.dump(Stdio.cout);
    }

}
