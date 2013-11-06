package net.bodz.bas.ant.task;

import java.io.IOException;

import org.junit.Assert;

import net.bodz.bas.ant.test.TaskTester;

public class CountersTaskTest
        extends Assert {

    // @Test
    public void test()
            throws IOException {
        new TaskTester(1).run();
    }

}
