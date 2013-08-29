package net.bodz.bas.ant.task;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.ant.test.TaskTestApp;

public class CountersTaskTest {

    @Test
    public void test()
            throws IOException {
        new TaskTestApp(1).run();
    }

}
