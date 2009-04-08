package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageTestApp;

import org.eclipse.swt.widgets.Composite;

public class DonePageTest {

    public static void main(String[] args) {
        final TestSession session = new TestSession();
        PageTestApp app = new PageTestApp() {
            @Override
            protected PageComposite createPage(Composite parent, int style) {
                return new DonePage(session, parent, style);
            }
        };
        app.run();
    }

}
