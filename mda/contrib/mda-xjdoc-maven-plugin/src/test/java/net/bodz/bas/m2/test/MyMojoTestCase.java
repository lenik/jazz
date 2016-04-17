package net.bodz.bas.m2.test;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.meta.codegen.CopyAndPaste;

public abstract class MyMojoTestCase
        extends AbstractMojoTestCase {

    /**
     * @see net.bodz.bas.c.m2.MavenProjectOrigin
     */
    @CopyAndPaste
    protected static File getResourceDir(Class<?> clazz) {
        File classFile = ClassResource.getClassBytesFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();

        int i;
        do {
            i = path.indexOf("/target/classes/");
            if (i != -1) {
                path = path.substring(0, i) + "/src/main/resources/";
                break;
            }

            i = path.indexOf("/target/test-classes/");
            if (i != -1) {
                path = path.substring(0, i) + "/src/test/resources/";
                break;
            }
        } while (false);

        return new File(path);
    }

    protected static File getResourceFile(Class<?> clazz, String spec) {
        File resourceDir = getResourceDir(clazz);
        String resourceName = clazz.getPackage().getName().replace('.', '/') + "/" + spec;
        File file = new File(resourceDir, resourceName);
        return file;
    }

    protected File getResourceFile(String spec) {
        return getResourceFile(getClass(), spec);
    }

}
