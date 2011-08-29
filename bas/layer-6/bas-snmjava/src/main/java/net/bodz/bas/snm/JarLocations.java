package net.bodz.bas.snm;

import java.io.File;
import java.net.URL;

import net.bodz.bas.util.io.FileURL;
import net.bodz.bas.util.loader.ClassResource;

/**
 * Here "Jar" may be referred to a jar file or classes directory.
 */
public class JarLocations {

    /**
     * jar file or the classpath directory.
     */
    public static File getBaseClasspath(Class<?> clazz) {
        String remove = clazz.getName().replace('.', '/') + ".class";
        URL url = ClassResource.classDataURL(clazz);
        return FileURL.getFile(url, remove);
    }

}
