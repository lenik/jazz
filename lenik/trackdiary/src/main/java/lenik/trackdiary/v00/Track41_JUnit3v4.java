package lenik.trackdiary.v00;

import junit.framework.TestCase;

import org.junit.Test;

public class Track41_JUnit3v4
        extends TestCase {

    static int nextId = 0;

    int id;
    String prefix;

    public Track41_JUnit3v4() {
        id = nextId++;

        int c = id % 20;
        String indent = "";
        while (c-- != 0)
            indent += " ";

        prefix = indent + Integer.toHexString(id);
        System.out.println(prefix + ": ctor()");
    }

    // @Override
    protected void setUp()
            throws Exception {
        System.out.println(prefix + ": setup");
    }

    // @Override
    protected void tearDown()
            throws Exception {
        System.out.println(prefix + ": teardown");
    }

    @Test
    public void testAnn1() {
        System.out.println(prefix + ": ann1");
    }

    @Test
    public void testAnn2() {
        System.out.println(prefix + ": ann2");
    }

    public void testNam1() {
        System.out.println(prefix + ": name1");
    }

    public void testNam2() {
        System.out.println(prefix + ": name2");
    }

}
