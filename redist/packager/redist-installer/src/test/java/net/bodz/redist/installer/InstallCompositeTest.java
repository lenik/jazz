package net.bodz.redist.installer;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.redist.installer.builtins.TestConfig;
import net.bodz.swt.c.pageflow.BadPathEvent;
import net.bodz.swt.c.pageflow.IBadPathListener;
import net.bodz.swt.c.test.WidgetTester;

public class InstallCompositeTest
        extends WidgetTester {

    @Test
    public void test()
            throws Exception {
        TestProject project = new TestProject();
        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();

        IFile outdir = new PojfFile(TestConfig.outDir);
        session.addResFolder(outdir);
        TestConfig.setTestBaseDir(session);

        InstallComposite c = new InstallComposite(session, body, SWT.BORDER);
        c.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                shell.dispose();
            }
        });
        System.out.println("Created: " + c);
    }

}
