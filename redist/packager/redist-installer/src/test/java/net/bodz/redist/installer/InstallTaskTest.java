package net.bodz.redist.installer;

import java.io.IOException;

import net.bodz.bas.ant.TaskTestApp;

import org.junit.Test;

public class InstallTaskTest {

    @Test
    public void test()
            throws IOException {
        new TaskTestApp(1).run();
    }

}
