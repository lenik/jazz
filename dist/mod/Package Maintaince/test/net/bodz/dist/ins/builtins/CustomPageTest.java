package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.TestSession;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.eclipse.swt.widgets.Composite;

public class CustomPageTest {

    public static void main(String[] args) {
        final TestSession session = new TestSession();
        PageTestApp app = new PageTestApp() {
            @Override
            protected PageComposite createPage(Composite parent, int style) {
                return new CustomPage(session, parent, style);
            }
        };
        app.run();
    }

}
