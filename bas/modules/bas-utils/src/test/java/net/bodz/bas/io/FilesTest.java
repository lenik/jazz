package net.bodz.bas.io;

import static net.bodz.bas.commons.collection.util.Arrays2._;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class FilesTest {

    private final File testDir;

    public FilesTest() {
        testDir = Files.getTmpDir();
    }

    @Test
    public void testGetClassFile() throws Exception {
        class D {
            void o(String input, String expected) throws IOException {
                String actual = Files.readAll(Files.classData(FilesTest.class, input), "utf-8"); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("data", "Hello, world!"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testObjs() throws Exception {
        // File tmpdir = new File(System.getenv("TEMP"));
        List<Object> t = new ArrayList<Object>();
        t.add("Hello"); //$NON-NLS-1$
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
        class D {
            void o(String pair, long expected) throws Throwable {
                int vert = pair.indexOf('|');
                byte[] src = pair.substring(0, vert).getBytes();
                byte[] dst = pair.substring(vert + 1).getBytes();
                long actual = Files.diff_1(src, dst);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("abcdefghijklmopqrstuvwxyz|abcdefghijklmopqrst", 19L); //$NON-NLS-1$
        d.o("abcdefghijklmopqrst|abcdefghijklmopqrst", -1L); //$NON-NLS-1$
        d.o("abcdefghijklmopqrst|-bcdefghijklmopqrst", 0L); //$NON-NLS-1$
    }

    @Test
    public void testGetRelativeName() {
        class D {
            void o(Object[] input, String expected) {
                File start = new File((String) input[0]);
                File file = new File((String) input[1]);
                String actual = Files.getRelativeName(file, start);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(_("a", "a/b"), "b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        d.o(_("a/", "a/b"), "b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        d.o(_("a/b", "a/b"), ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    void testFindBase(String expected, String a, String b) {
        File fa = a == null ? null : new File(a);
        File fb = b == null ? null : new File(b);
        File resultf = Files.findBase(fa, fb);
        String result = resultf == null ? null : resultf.toString(); // getPath();
        if (result != null)
            result = result.replace('\\', '/');
        assertEquals(expected, result);
    }

    @Test
    public void testFindBase() throws Exception {
        testFindBase("a/b", "a/b/c/d", "a/b/x/y"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase("a/b", "a/b/c/d/e/f/g/h/i/j/k", "a/b/x/c/d/e/f/g/h/i/j/k"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase("a/b", "a/b/", "a/b/x/y"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase("a/b", "a/b", "a/b/x/y"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase("a/b", "a/b/c/d", "a/b/"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase("a/b", "a/b/c/d", "a/b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase("a", "a/b/c/d", "a"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        testFindBase(null, "a/b/c", "d/e/f"); //$NON-NLS-1$ //$NON-NLS-2$
        testFindBase("a", "a/b/../x", "a/x/"); // don't use canonical. //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    File file(String path) {
        return Files.canoniOf(testDir, path);
    }

    void create(String path, Object data) throws IOException {
        File f = file(path);
        f.getParentFile().mkdirs();
        Files.write(f, data);
    }

    @Test
    public void testDeleteTree() throws IOException {
        create("dt/a/b/c", "1"); //$NON-NLS-1$ //$NON-NLS-2$
        create("dt/d/e/f", "1"); //$NON-NLS-1$ //$NON-NLS-2$
        create("dt/d/g/h", "1"); //$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("envprep", file("dt/d/e").isDirectory()); //$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("envprep", file("dt/d/g/h").isFile()); //$NON-NLS-1$ //$NON-NLS-2$
        Files.deleteTree(file("dt")); //$NON-NLS-1$
        assertFalse(file("dt").exists()); //$NON-NLS-1$
    }

    @Test
    public void testFind1() {
        List<File> find = Files.find("K:/workspace/net.bodz/*/mod/*/src/net/bodz"); //$NON-NLS-1$
        if (find == null)
            System.out.println("no match"); //$NON-NLS-1$
        else
            System.out.println(Strings.join("\n", find)); //$NON-NLS-1$
    }

    @Test
    public void testGetFile2() throws IOException {
        Class<?>[] classes = {
        //
                getClass(),//
                String.class, //
        };
        for (Class<?> clazz : classes) {
            String p = clazz.getName().replace('.', '/') + ".class"; //$NON-NLS-1$
            URL url = Files.classData(clazz);
            File file = Files.getFile(url, p);
            System.out.println(clazz + " -> " + file); //$NON-NLS-1$
        }
    }

    @Test
    public void testReadByBlock() throws Exception {
        byte[] src = "0123456789abcdefghijklmnopqrstuvwxyz".getBytes(); //$NON-NLS-1$
        ByteArrayInputStream in = new ByteArrayInputStream(src);
        Iterator<byte[]> iter = Files.readByBlock(5, in).iterator();
        assertEquals("01234", new String(iter.next())); //$NON-NLS-1$
        assertEquals("56789", new String(iter.next())); //$NON-NLS-1$
        assertEquals("abcde", new String(iter.next())); //$NON-NLS-1$
        assertEquals("fghij", new String(iter.next())); //$NON-NLS-1$
        assertEquals("klmno", new String(iter.next())); //$NON-NLS-1$
        assertEquals("pqrst", new String(iter.next())); //$NON-NLS-1$
        assertEquals("uvwxy", new String(iter.next())); //$NON-NLS-1$
        assertEquals("z", new String(iter.next())); //$NON-NLS-1$
    }

}
