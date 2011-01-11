package net.bodz.bas.ant;

import java.io.IOException;

import org.junit.Test;

public class CountersTaskTest {

    @Test
    public void test() throws IOException {
        new TaskTestApp(1).run();
    }

}
