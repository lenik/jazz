package net.bodz.pkg.sis;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.ant.test.TaskTestApp;

public class InstallTaskTest {

    @Test
    public void test()
            throws IOException {
        new TaskTestApp(1).run();
    }

}
