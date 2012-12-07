package net.bodz.bas.test.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JUnitAppTest
        extends JUnitApp<JUnitAppTest> {

    List<String> list;

    public JUnitAppTest() {
        list = new ArrayList<String>();
    }

    @Override
    public void setUp() {
        list.add("set up");

        List<String> expected = Arrays.asList("set up");
        assertEquals(expected, list);
    }

    @Override
    public void tearDown() {
        list.add("tear down");

        List<String> expected = Arrays.asList("set up", "test 1", "tear down");
        assertEquals(expected, list);
    }

    @Test
    public void test1() {
        list.add("test 1");

        List<String> expected = Arrays.asList("set up", "test 1");
        assertEquals(expected, list);
    }

    public static void main(String[] args) {
        new JUnitAppTest().assemble().test1();
    }

}
