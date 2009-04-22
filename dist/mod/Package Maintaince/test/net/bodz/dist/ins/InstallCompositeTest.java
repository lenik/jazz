package net.bodz.dist.ins;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.junit.Test;

public class InstallCompositeTest {

    @Test
    public void test() throws Exception {
        ControlTestApp app = new ControlTestApp();

        TestProject project = new TestProject();
        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();

        InstallComposite c = new InstallComposite(session, app.parent,
                SWT.BORDER);
        System.out.println("Created: " + c);

        app.run();
    }

}
