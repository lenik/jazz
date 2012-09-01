package net.bodz.redist.installer;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.ant.TaskTestApp;

public class InstallTaskTest {

    @Test
    public void test()
            throws IOException {
        new TaskTestApp(1).run();
    }

}
