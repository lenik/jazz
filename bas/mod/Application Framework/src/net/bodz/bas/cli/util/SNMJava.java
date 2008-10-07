package net.bodz.bas.cli.util;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.UnexpectedException;

public class SNMJava {

    public static URL findSrc(Class<?> clazz, String srcExt) {
        URL classData = Files.classData(clazz);
        String path = classData.toString();
        int dot = path.lastIndexOf('.');
        assert dot != -1;
        // assert ".class".path.substring(dot);
        path = path.substring(0, dot);

        int classNameLen = clazz.getName().length();
        int slash = path.length() - classNameLen - 1;
        assert path.charAt(slash) == '/';
        // <bindir> <respath>
        // ...jar! /a/b/c.class
        // ...dir /a/b/c.class
        String bindir = path.substring(0, slash);
        String respath = path.substring(slash);

        slash = bindir.lastIndexOf('/');
        assert slash != -1;
        // <dir> <base>
        // ...foo/ bar.jar!
        // ...foo/ bar
        String dir = bindir.substring(0, slash + 1);
        String base = bindir.substring(slash + 1);

        String protocol = classData.getProtocol();
        if ("jar".equals(protocol)) {
            // trailing `!'
            dot = base.lastIndexOf('.');
            String baseName = base.substring(0, dot);
            String baseExt = base.substring(dot);
            base = baseName + "-source" + baseExt;
        } else {
            if ("bin".equals(base))
                base = "src";
            else if (base.endsWith(".bin"))
                base = base.substring(0, base.length() - 4);
            else
                base += "-src";
        }
        String srcdir = dir + base;
        String srcurl = srcdir + respath + srcExt;
        try {
            return new URL(srcurl);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    public static URL findSrc(Class<?> clazz) {
        return findSrc(clazz, ".java");
    }

}
