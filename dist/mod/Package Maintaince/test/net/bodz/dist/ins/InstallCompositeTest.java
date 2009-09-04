package net.bodz.dist.ins;

import net.bodz.bas.io.FileResFolder;
import net.bodz.dist.ins.builtins.TestConfig;
import net.bodz.swt.gui.pfl.BadPathEvent;
import net.bodz.swt.gui.pfl.BadPathListener;
import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.junit.Test;

public class InstallCompositeTest {

    @Test
    public void test() throws Exception {
        final ControlTestApp app = new ControlTestApp();

        TestProject project = new TestProject();
        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();

        session.addResFolder(new FileResFolder(TestConfig.outDir));
        TestConfig.setTestBaseDir(session);

        InstallComposite c = new InstallComposite(session, app.parent, SWT.BORDER);
        c.getPageFlow().addBadPathListener(new BadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                app.shell.dispose();
            }
        });
        System.out.println("Created: " + c); //$NON-NLS-1$

        app.run();
    }

}
