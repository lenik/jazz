package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

public class DefaultScanOptions
        extends AbstractScanOptions {

    List<String> includeDirPaths = new ArrayList<>();
    List<String> excludeDirPaths = new ArrayList<>();
    boolean excludeJarPaths = true;

    Set<String> excludeDirNames = new HashSet<>();

    public DefaultScanOptions() {
        excludeDirNames.add("node_modules");
        excludeDirNames.add("libjs");
    }

    public void addDirToInclude(String dirPath) {
        includeDirPaths.add(dirPath);
    }

    public void addDirToExclude(String dirPath) {
        excludeDirPaths.add(dirPath);
    }

    @Override
    public boolean acceptDirEntry(File file) {
        String name = file.getName();
        if (excludeDirNames.contains(name))
            return false;

        String path = file.getPath();

        for (String exclude : excludeDirPaths) {
            String exclude_ = exclude + File.separatorChar;
            if (path.startsWith(exclude_))
                return false;
        }

        if (! includeDirPaths.isEmpty()) {
            boolean any = false;
            for (String include : includeDirPaths) {
                String include_ = include + File.separatorChar;
                if (path.startsWith(include_)) {
                    any = true;
                    break;
                }
            }
            if (! any)
                return false;
        }
        return true;
    }

    @Override
    public boolean acceptJarPath(JarFile jarFile) {
        if (excludeJarPaths)
            return false;
        return false;
    }

    @Override
    public boolean acceptPath(File dir) {
        if (! acceptPath(dir.getName()))
            return false;
        return true;
    }

    @Override
    public boolean acceptPath(String dirName) {
        if (excludeDirNames.contains(dirName))
            return false;
        return true;
    }

}
