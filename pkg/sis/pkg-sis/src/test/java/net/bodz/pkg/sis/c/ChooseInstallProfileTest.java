package net.bodz.pkg.sis.c;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.pkg.sis.TestSisProject;
import net.bodz.swt.test.PageTester;

public class ChooseInstallProfileTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        final TestSisProject project = new TestSisProject();
        ChooseInstallProfile cc = new ChooseInstallProfile(project);

        PageTester app = new ConfigPageTester(cc);

        app.run();
    }

}
