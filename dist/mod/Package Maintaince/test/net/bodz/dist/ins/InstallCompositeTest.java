package net.bodz.dist.ins;

import net.bodz.bas.io.FileResFolder;
import net.bodz.swt.gui.pfl.WizardExitEvent;
import net.bodz.swt.gui.pfl.WizardExitListener;
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
        c.addExitListener(new WizardExitListener() {
            @Override
            public void wizardExit(WizardExitEvent e) {
                app.shell.dispose();
            }
        });
        System.out.println("Created: " + c); //$NON-NLS-1$

        app.run();
    }

}
