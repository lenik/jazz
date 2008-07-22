package net.bodz.bas.io;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.types.util.Arrays2._;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class FilesTest {

    @Test
    public void testGetClassFile() throws Exception {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Files.readAll(Files.classData(FilesTest.class, input),
                        "utf-8");
            }
        }, //
                EQ("data", "Hello, world!"));
    }

    @Test
    public void testObjs() throws Exception {
        // File tmpdir = new File(System.getenv("TEMP"));
        List<Object> t = new ArrayList<Object>();
        t.add("Hello");
        t.add(new Integer(3));
        t.add('M');
        t.add('i');
        t.add('c');
        t.add('e');

        byte[] buf = Files.dumpBytes(t);
        Object s = Files.load(buf);
        assertEquals(t, s);
    }

    static {
        Files.blockSize = 4;
    }

    @Test
    public void testDiff() throws Throwable {
        TestDefs.tests(new TestEval<String>() {
            public Long eval(String pair) throws Throwable {
                int vert = pair.indexOf('|');
                byte[] src = pair.substring(0, vert).getBytes();
                byte[] dst = pair.substring(vert + 1).getBytes();
                return Files.diff_1(src, dst);
            }
        }, //
                EQ("abcdefghijklmopqrstuvwxyz|abcdefghijklmopqrst", 19L), //
                EQ("abcdefghijklmopqrst|abcdefghijklmopqrst", -1L), //
                EQ("abcdefghijklmopqrst|-bcdefghijklmopqrst", 0L));
    }

    @Test
    public void testGetRelativeName() {
        TestDefs.tests(new TestEval<Object[]>() {
            public Object eval(Object[] input) throws Throwable {
                File start = new File((String) input[0]);
                File file = new File((String) input[1]);
                return Files.getRelativeName(file, start);
            }
        }, //
                EQ(_("a", "a/b"), "b"), //
                EQ(_("a/", "a/b"), "b"), //
                EQ(_("a/b", "a/b"), ""), //
                END);
    }
}
