package net.bodz.bas.files;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class FileResTest {

    @Test
    public void testGetClassFile()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws IOException {
                URL classData = FileRes.classData(FileResTest.class, input);
                // IStreamInputSource source=null; // new URLResource();
                // String actual = Files.readAll(, "utf-8");
                // assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("data", "Hello, world!");
    }

    @Test
    public void testGetFile2()
            throws IOException {
        Class<?>[] classes = {
        //
                getClass(),//
                String.class, //
        };
        for (Class<?> clazz : classes) {
            String p = clazz.getName().replace('.', '/') + ".class";
            URL url = FileRes.classData(clazz);
            File file = FilePath.getFile(url, p);
            System.out.println(clazz + " -> " + file);
        }
    }

}
