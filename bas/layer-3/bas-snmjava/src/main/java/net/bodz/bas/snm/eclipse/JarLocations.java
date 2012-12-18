package net.bodz.bas.snm.eclipse;

import java.io.File;
import java.net.URL;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.loader.ClassResource;

/**
 * Here "Jar" may be referred to a jar file or classes directory.
 */
public class JarLocations {

    /**
     * jar file or the classpath directory.
     */
    public static File getBaseClasspath(Class<?> clazz) {
        String remove = clazz.getName().replace('.', '/') + ".class";
        URL url = ClassResource.getClassBytesURL(clazz);
        return FileURL.toFile(url, remove);
    }

}
