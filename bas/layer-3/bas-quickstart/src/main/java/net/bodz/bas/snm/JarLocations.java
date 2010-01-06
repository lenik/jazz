package net.bodz.bas.snm;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

/**
 * Here "Jar" may be referred to a jar file or classes directory.
 */
public class JarLocations {

    /**
     * jar file or the classpath directory.
     */
    public static File getBaseClasspath(Class<?> clazz) {
        String p = clazz.getName().replace('.', '/') + ".class"; 
        URL url = Files.classData(clazz);
        try {
            File base = Files.getFile(url, p);
            return base;
        } catch (Exception e) {
            throw new IllegalArgumentException("The java class maybe not loaded in a normal way: URL=" + url, e); 
        }
    }

    /**
     * @return File directory or Jar file as the resource container.
     */
    public static ResFolder getResBase(Class<?> clazz) {
        String classFileName = clazz.getName().replace('.', '/') + ".class"; 
        URL url = Files.classData(clazz);
        try {
            ResFolder base = Files.getResFolder(url, classFileName);
            return base;
        } catch (Exception e) {
            throw new IllegalArgumentException("The java class maybe not loaded in a normal way: URL=" + url, e); 
        }
    }

}
