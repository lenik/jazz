package net.bodz.mda.loader;

import static org.junit.Assert.assertEquals;

public class GroovyCompileClassLoaderTest {

    ClassLoader loader;

    // @Test
    public void testFindClassString() throws Throwable {
        TestClasses classes = new TestClasses(loader, "1");
        while (classes.next()) {
            // String java = classes.getJava();
            String name = classes.getName();
            String expected = classes.getExpected();

            // compiler.compile(java, name);
            String actual = classes.new2str(name);
            System.out.println(name + ": " + actual);
            assertEquals(name, expected, actual);
        }
    }

}
