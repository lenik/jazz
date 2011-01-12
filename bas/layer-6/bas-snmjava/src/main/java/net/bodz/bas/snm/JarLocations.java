package net.bodz.bas.snm;

import java.io.File;

import net.bodz.bas.util.ClassResource;

/**
 * Here "Jar" may be referred to a jar file or classes directory.
 */
public class JarLocations {

    /**
     * jar file or the classpath directory.
     */
    public static File getBaseClasspath(Class<?> clazz) {
        String p = clazz.getName().replace('.', '/') + ".class";
        ClassResource.classData(clazz);
        return null;
    }

}
