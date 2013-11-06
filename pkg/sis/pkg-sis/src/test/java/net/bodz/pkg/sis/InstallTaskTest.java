package net.bodz.pkg.sis;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.ant.test.TaskTester;

public class InstallTaskTest {

    @Test
    public void test()
            throws IOException {
        new TaskTester(1).run();
    }

}
