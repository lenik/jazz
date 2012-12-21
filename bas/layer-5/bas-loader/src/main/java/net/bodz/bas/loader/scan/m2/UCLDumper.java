package net.bodz.bas.loader.scan.m2;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.FileURL;

public class UCLDumper {

    public static List<File> getLocalClasspaths() {
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        return getLocalClasspaths(scl);
    }

    public static List<File> getLocalClasspaths(ClassLoader cl) {
        List<File> localFileList = new ArrayList<File>();
        while (cl != null) {
            if (cl instanceof URLClassLoader) {
                URLClassLoader ucl = (URLClassLoader) cl;
                for (URL url : ucl.getURLs()) {
                    File localFile = FileURL.toFile(url, null);
                    if (localFile != null)
                        localFileList.add(localFile);
                }
            }

            cl = cl.getParent();
        }
        return localFileList;
    }

    static boolean dumpRaw = true;

    public static void main(String[] args) {
        if (dumpRaw) {
            ClassLoader scl = ClassLoader.getSystemClassLoader();
            ClassLoader cl = scl;
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
