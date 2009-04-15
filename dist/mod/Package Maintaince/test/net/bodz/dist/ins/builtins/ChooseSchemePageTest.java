package net.bodz.dist.ins.builtins;

import java.io.IOException;

import net.bodz.dist.ins.ProgressPage;
import net.bodz.dist.ins.TestProject;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.eclipse.swt.widgets.Composite;

public class ChooseSchemePageTest {

    public static void main(String[] args) throws IOException {
        final TestProject project = new TestProject();
        PageTestApp app = new PageTestApp() {
            @Override
            protected PageComposite createPage(Composite parent, int style) {
                final GUISession session = new GUISession(project, null);
                ProgressPage progress = new ProgressPage(session, parent, style);
                ChooseSchemePage csp = new ChooseSchemePage(session, parent,
                        style);
                return csp;
            }
        };
        app.run();
    }

}
