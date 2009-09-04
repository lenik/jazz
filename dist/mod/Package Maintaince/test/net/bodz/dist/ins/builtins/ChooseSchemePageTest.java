package net.bodz.dist.ins.builtins;

import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Session;
import net.bodz.dist.ins.TestProject;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.junit.Test;

public class ChooseSchemePageTest {

    @Test
    public void test1() throws Exception {
        final TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, LogTerms.console);
        PageTestApp app = new PageTestApp(new ChooseSchemePage(project, session));
        app.run();
    }

}
