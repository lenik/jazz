package net.bodz.art.installer.builtins;

import net.bodz.art.installer.ISession;
import net.bodz.art.installer.Session;
import net.bodz.art.installer.TestProject;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.swt.c3.test.PageTestApp;

import org.junit.Test;

public class ChooseSchemePageTest {

    @Test
    public void test1()
            throws Exception {
        final TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, ConsoleLogger.getInstance());
        PageTestApp app = new PageTestApp(new ChooseSchemePage(project, session));
        app.run();
    }

}
