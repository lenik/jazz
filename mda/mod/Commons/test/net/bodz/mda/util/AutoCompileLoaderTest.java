package net.bodz.mda.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.util.Classpath;

import org.junit.Test;

public class AutoCompileLoaderTest {

    File              dir;
    JavaCompiler      compiler;
    ClassLoader       loader0;
    AutoCompileLoader loader;

    public AutoCompileLoaderTest() throws Exception {
        String javaHome = System.getenv("JAVA_HOME");
        File tools = new File(javaHome, "lib/tools.jar");
        Classpath.addURL(tools.toURI().toURL());

        dir = new File(Files.getTmpDir(), "acl");
        compiler = new JavaCompiler(dir);

        loader0 = Caller.getCallerClassLoader();
        loader = new AutoCompileLoader(compiler, loader0);

        Files.deleteTree(dir);
        dir.mkdirs();
    }

    @Test
    public void testCompile() throws Throwable {
        TestClasses classes = new TestClasses(loader, "1");
        while (classes.next()) {
            String java = classes.getJava();
            String name = classes.getName();
            String expected = classes.getExpected();

            compiler.compile(java, name);
            String actual = classes.new2str(name);
            System.out.println(name + ": " + actual);
            assertEquals(name, expected, actual);
        }
    }

}
