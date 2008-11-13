package net.bodz.bas.snm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.util.Strings;

public class SJProject {

    public static File findProjectBase(Class<?> clazz) {
        File base = findBase(clazz);
        while (base != null) {
            if (new File(base, ".project").exists())
                return base;
            base = base.getParentFile();
        }
        return null;
    }

    static class URLInfo {
        String prefix;
        String prefixDir;
        String prefixBase;
        String respath;
        String protocol;
    }

    static URLInfo getInfo(Class<?> clazz) {
        URL classData = Files.classData(clazz);
        String path = classData.toString();
        int dot = path.lastIndexOf('.');
        assert dot != -1;
        // assert ".class".path.substring(dot);
        path = path.substring(0, dot);

        int classNameLen = clazz.getName().length();
        int slash = path.length() - classNameLen - 1;
        assert path.charAt(slash) == '/';
        // <prefix> <respath>
        // ...jar! /a/b/c.class
        // ...dir /a/b/c.class
        URLInfo info = new URLInfo();
        info.prefix = path.substring(0, slash);
        info.respath = path.substring(slash);
        info.protocol = classData.getProtocol();

        String prefix = info.prefix;
        slash = prefix.lastIndexOf('/');
        assert slash != -1;
        // <dir> <base>
        // ...foo/ bar.jar!
        // ...foo/ bar
        info.prefixDir = prefix.substring(0, slash + 1);
        info.prefixBase = prefix.substring(slash + 1);
        return info;
    }

    /**
     * start point of bindir.
     */
    public static File findBase(Class<?> clazz) {
        URLInfo info = getInfo(clazz);
        String baseURL = info.prefix;
        if ("jar".equals(info.protocol)) {
            baseURL = Strings.chop(baseURL);
            baseURL = baseURL.substring(4); // remove "jar:"
        }
        URI uri;
        try {
            uri = new URI(baseURL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return Files.canoniOf(uri);
    }

    public static URL findSrc(Class<?> clazz, String srcExt) {
        URLInfo info = getInfo(clazz);
        String base = info.prefixBase;
        if ("jar".equals(info.protocol)) {
            // trailing `!'
            int dot = base.lastIndexOf('.');
            String baseName = base.substring(0, dot);
            String baseExt = base.substring(dot);
            base = baseName + "-src" + baseExt;
        } else {
            if ("bin".equals(base))
                base = "src";
            else if (base.endsWith(".bin"))
                base = base.substring(0, base.length() - 4);
            else
                base += "-src";
        }
        String srcdir = info.prefixDir + base;
        String srcurl = srcdir + info.respath + srcExt;
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
