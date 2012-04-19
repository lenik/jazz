package net.bodz.bas.loader.scan.m2;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class UCLDumper {

    public static List<File> getLocalClasspaths() {
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        return getLocalClasspaths(scl);
    }

    public static List<File> getLocalClasspaths(ClassLoader cl) {
        List<File> list = new ArrayList<File>();
        while (cl != null) {
            if (cl instanceof URLClassLoader) {
                URLClassLoader ucl = (URLClassLoader) cl;
                for (URL url : ucl.getURLs()) {
                    if ("file".equals(url.getProtocol())) {
                        URI uri;
                        try {
                            uri = url.toURI();
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e.getMessage(), e);
                        }
                        File file = new File(uri);
                        list.add(file);
                    }
                }
            }

            cl = cl.getParent();
        }
        return list;
    }

    static boolean dumpRaw = true;

    public static void main(String[] args) {
        if (dumpRaw) {
            ClassLoader scl = ClassLoader.getSystemClassLoader();
            ClassLoader cl = (URLClassLoader) scl;
            while (cl != null) {
                System.out.println(cl);

                if (cl instanceof URLClassLoader) {
                    URLClassLoader ucl = (URLClassLoader) cl;
                    for (URL url : ucl.getURLs()) {
                        System.out.println("    " + url);
                    }
                }

                cl = cl.getParent();
                System.out.println();
            }
        } else
            for (File file : getLocalClasspaths())
                if (file.isDirectory())
                    System.out.println(file);
    }

}
