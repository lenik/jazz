package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.Session;
import net.bodz.redist.installer.TestProject;
import net.bodz.bas.gui.ia.ConsoleInteraction;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.swt.c3.test.PageTestApp;

import org.junit.Test;

public class ChooseSchemePageTest {

    @Test
    public void test1()
            throws Exception {
        final TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleInteraction.stdout, ConsoleLogger.getInstance());
        PageTestApp app = new PageTestApp(new ChooseSchemePage(project, session));
        app.run();
    }

}
