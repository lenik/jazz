package net.bodz.dist.ins;

import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.util.LogTerms;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.junit.Test;

public class DonePageTest {

    @Test
    public void test() throws Exception {
        final Project project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, LogTerms.console);
        PageTestApp app = new PageTestApp(new DonePage(session));
        app.run();
    }

}
