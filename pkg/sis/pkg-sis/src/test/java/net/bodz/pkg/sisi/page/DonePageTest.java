package net.bodz.pkg.sisi.page;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.pkg.sis.ISisProject;
import net.bodz.pkg.sis.TestSisProject;
import net.bodz.swt.test.PageTester;

public class DonePageTest
        extends Assert {

    @Test
    public void test()
            throws Exception {
        final ISisProject project = new TestSisProject();
        PageTester app = new PageTester(new DonePage(project));
        app.run();
    }

}
