package net.bodz.pkg.sis.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.pkg.sis.TestSisProject;

public class SisDumperTest
        extends Assert {

    @Test
    public void test()
            throws IOException {
        TestSisProject project = new TestSisProject();
        SisDumper.dump(TreeOutImpl.from(Stdio.cout), project);
    }

}
