package net.bodz.art.installer;

import net.bodz.art.installer.builtins.TestConfig;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.swt.gui.pfl.BadPathEvent;
import net.bodz.swt.gui.pfl.IBadPathListener;
import net.bodz.swt.widgets.test.ControlTestApp;

import org.eclipse.swt.SWT;
import org.junit.Test;

public class InstallCompositeTest {

    @Test
    public void test()
            throws Exception {
        final ControlTestApp app = new ControlTestApp();

        TestProject project = new TestProject();
        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();

        IFile outdir = new JavaioFile(TestConfig.outDir);
        session.addResFolder(outdir);
        TestConfig.setTestBaseDir(session);

        InstallComposite c = new InstallComposite(session, app.holder, SWT.BORDER);
        c.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                app.shell.dispose();
            }
        });
        System.out.println("Created: " + c);

        app.run();
    }

}
