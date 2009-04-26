package net.bodz.dist.ins;

import java.io.IOException;

import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.DonePage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Project;
import net.bodz.dist.ins.Session;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.eclipse.swt.widgets.Composite;

public class DonePageTest {

    public static void main(String[] args) throws IOException {
        final Project project = new TestProject();
        final ISession session = new Session(project, ConsoleUI.stdout, LogTerms.console);
        PageTestApp app = new PageTestApp() {
            @Override
            protected PageComposite createPage(Composite parent, int style) {
                return new DonePage(session, parent, style);
            }
        };
        app.run();
    }

}
