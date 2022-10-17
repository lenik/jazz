package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ResourceScanner {

    static final Logger logger = LoggerFactory.getLogger(ResourceScanner.class);

    private ClassLoader classLoader;
    private IFileOrEntryFilter filter;

    List<String> includeDirPaths = new ArrayList<>();
    List<String> excludeDirPaths = new ArrayList<>();
    boolean excludeJarPaths = true;

    Set<String> excludeDirNames = new HashSet<>();

    public ResourceScanner(ClassLoader classLoader, IFileOrEntryFilter filter) {
        this.classLoader = classLoader;
        setFilter(filter);

        // default excludes
        excludeDirNames.add("node_modules");
        excludeDirNames.add("libjs");
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        if (classLoader == null)
            throw new NullPointerException("classLoader");
        this.classLoader = classLoader;
    }

    public IFileOrEntryFilter getFilter() {
        return filter;
    }

    public void setFilter(IFileOrEntryFilter filter) {
        if (filter == null)
            filter = IFileOrEntryFilter.TRUE;
        this.filter = filter;
    }

    public void addDirToInclude(String dirPath) {
        includeDirPaths.add(dirPath);
    }

    public void addDirToExclude(String dirPath) {
        excludeDirPaths.add(dirPath);
    }

    public Map<String, List<URL>> scanResources(String rootResourceName)
            throws IOException {
        WhereMapProcessor wmap = new WhereMapProcessor();
        scanResources(rootResourceName, wmap);
        return wmap.getResourceMap();
    }

    public void scanResources(String resourceName, IFileOrEntryProcessor processor)
            throws IOException {
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            // System.out.println(url);
            if ("jar".equals(url.getProtocol())) {
                if (excludeJarPaths)
                    continue;

                String path = url.getPath(); // "file:....!/..."
                int exclm = path.lastIndexOf('!');
                String jarPath = path.substring(5, exclm); // remove "file:"

                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.getName().startsWith(resourceName))
                        continue;
                    if (filter.accept(entry)) {
                        processor.process(jarFile, entry);
                    }
                }
            } else if ("file".equals(url.getProtocol())) {
                String path = url.getPath();
                File file = new File(path);
                if (isIncluded(file))
                    scanLocalDir(file, resourceName, processor);
                else
                    logger.debug("excluded: " + url);
            }
        }
    }

    void scanLocalDir(File dir, String resourcePath, IFileOrEntryProcessor processor) {
        logger.debug("Scan-LocalDir: ", dir);
        String resourcePath_ = resourcePath;
        if (!resourcePath_.isEmpty())
            resourcePath_ += "/";

        for (File childFile : dir.listFiles(filter)) {
            if (!isIncluded(childFile))
                continue;
            String name = childFile.getName();
            String childPath = resourcePath_ + name;
            if (childFile.isDirectory()) {
                scanLocalDir(childFile, childPath, processor);
            } else {
                processor.process(childFile, childPath);
            }
        }
    }

    boolean isIncluded(File file) {
        String path = file.getPath();

        if (file.isDirectory()) {
            String name = file.getName();
            if (excludeDirNames.contains(name))
                return false;
        }

        for (String dirPath : excludeDirPaths) {
            String prefix = dirPath + File.separatorChar;
            if (path.startsWith(prefix))
                return false;
        }

        if (!includeDirPaths.isEmpty()) {
            boolean any = false;
            for (String dirPath : includeDirPaths) {
                String prefix = dirPath + File.separatorChar;
                if (path.startsWith(prefix)) {
                    any = true;
                    break;
                }
            }
            if (!any)
                return false;
        }
        return true;
    }

}
