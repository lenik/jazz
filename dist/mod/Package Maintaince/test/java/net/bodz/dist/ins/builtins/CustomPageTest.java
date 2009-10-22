package net.bodz.dist.ins.builtins;

import net.bodz.bas.io.CharOuts;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Session;
import net.bodz.dist.ins.TestProject;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.junit.Test;

public class CustomPageTest {

    @Test
    public void test() throws Exception {
        TestProject project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, LogTerms.console);
        PageTestApp app = new PageTestApp(new CustomPage(project, session));
        app.run();
        session.dump(CharOuts.stdout);
    }

}
