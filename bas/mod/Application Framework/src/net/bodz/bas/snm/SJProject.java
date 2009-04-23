package net.bodz.bas.snm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.util.Strings;

public class SJProject {

    public static File findProjectBase(File dir) {
        if (dir != null && !dir.isDirectory())
            return null;
        while (dir != null) {
            if (new File(dir, ".project").exists()) //$NON-NLS-1$
                return dir;
            dir = dir.getParentFile();
        }
        return null;
    }

    public static File findProjectBase(Class<?> clazz) {
        File base = getOutputBase(clazz);
        return findProjectBase(base);
    }

    /**
     * jar file or the classpath directory.
     */
    public static File getOutputBase(Class<?> clazz) {
        String p = clazz.getName().replace('.', '/') + ".class";
        URL url = Files.classData(clazz);
        try {
            File base = Files.getFile(url, p);
            return base;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "The java class maybe not loaded in a normal way: URL=" + url, e);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    public static File getSrcBase(Class<?> clazz, String... srcNames) {
        File cp = getOutputBase(clazz);
        File parent = cp.getParentFile();
        File fallback = null;
        if (cp.isFile()) {
            String name_ = Files.getName(cp);
            String ext = Files.getExtension(cp, true);
            for (String n : srcNames) {
                String srcFileName = name_ + '-' + n + ext;
                File srcFile = new File(parent, srcFileName);
                if (srcFile.isFile())
                    return srcFile;
                if (fallback == null)
                    fallback = srcFile;
            }
        } else {
            String cpName = cp.getName();
            for (String n : srcNames) {
                String srcDirName;
                if ("bin".equals(cpName)) //$NON-NLS-1$
                    srcDirName = n;
                else if (cpName.endsWith(".bin")) //$NON-NLS-1$
                    srcDirName = Strings.chop(cpName, 4);
                else
                    srcDirName = cpName + '-' + n;
                File sourceDir = new File(parent, srcDirName);
                if (sourceDir.isDirectory())
                    return sourceDir;
                if (fallback == null)
                    fallback = sourceDir;
            }
        }
        return fallback;
    }

    public static File getSrcBase(Class<?> clazz) {
        return getSrcBase(clazz, "src", "source", "java");
    }

    /**
     * @param extension
     *            should include dot(.)
     */
    public static URL getSrcURL(Class<?> clazz, String extension) {
        // proguard mapping files...?
        String srcEntry = clazz.getName().replace('.', '/');
        int dollar = srcEntry.indexOf('$');
        if (dollar != -1)
            srcEntry = srcEntry.substring(0, dollar);
        srcEntry += extension;

        File srcBase = getSrcBase(clazz);
        if (srcBase.isDirectory()) {
            File srcFile = new File(srcBase, srcEntry);
            return Files.getURL(srcFile);
        } else {
            String jarURL = "jar:" + srcBase.toURI().toString() + "!/" + srcEntry;
            try {
                return new URL(jarURL);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static URL getSrcURL(Class<?> clazz) {
        return getSrcURL(clazz, ".java"); //$NON-NLS-1$
    }

}
