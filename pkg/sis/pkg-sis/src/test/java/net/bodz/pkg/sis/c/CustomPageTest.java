package net.bodz.pkg.sis.c;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.pkg.sis.TestSisProject;
import net.bodz.pkg.sis.c.CustomComponents;
import net.bodz.swt.test.PageTester;

public class CustomPageTest
        extends Assert {

    @Test
    public void test()
            throws Exception {
        TestSisProject project = new TestSisProject();
        PageTester app = new ConfigPageTester(new CustomComponents(project));
        app.run();
    }

}
