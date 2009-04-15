package net.bodz.dist.ins.builtins;

import java.io.IOException;

import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.TestProject;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.eclipse.swt.widgets.Composite;

public class CustomPageTest {

    public static void main(String[] args) throws IOException {
        TestProject project = new TestProject();
        final ISession session = new GUISession(project, null);
        PageTestApp app = new PageTestApp() {
            @Override
            protected PageComposite createPage(Composite parent, int style) {
                return new CustomPage(session, parent, style);
            }
        };
        app.run();
    }

}
