package net.bodz.pkg.sisi.page;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.bas.c.action.IProgressMonitor;
import net.bodz.pkg.sis.ConsoleProgressMonitor;
import net.bodz.pkg.sis.TestSisProject;
import net.bodz.pkg.sis.test.TestConfig;
import net.bodz.swt.c.pageflow.BadPathEvent;
import net.bodz.swt.c.pageflow.IBadPathListener;
import net.bodz.swt.c.test.WidgetTester;

public class InstallNavigatorTest
        extends WidgetTester {

    @Test
    public void test()
            throws Exception {
        TestSisProject project = new TestSisProject();
        project.setArchive(TestConfig.outDir);

        TestConfig.setTestBaseDir(project);

        SisiNavigator c = new SisiNavigator(project, body, SWT.BORDER);
        c.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                shell.dispose();
            }
        });

        IProgressMonitor monitor = new ConsoleProgressMonitor();

        System.out.println("Created: " + c);
    }

}
