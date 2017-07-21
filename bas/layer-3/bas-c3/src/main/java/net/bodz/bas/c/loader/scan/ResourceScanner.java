package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceScanner {

    private ClassLoader classLoader;
    private IFileOrEntryFilter filter;

    public ResourceScanner(ClassLoader classLoader, IFileOrEntryFilter filter) {
        this.classLoader = classLoader;
        setFilter(filter);
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

    public Map<String, List<URL>> scanResources(String rootResourceName)
            throws IOException {
        BufferedFileOrEntryProcessor buffer = new BufferedFileOrEntryProcessor();
        scanResources(rootResourceName, buffer);
        return buffer.getResourceMap();
    }

    public void scanResources(String rootResourceName, IFileOrEntryProcessor processor)
            throws IOException {
        Enumeration<URL> urls = classLoader.getResources(rootResourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            // System.out.println(url);
            if ("jar".equals(url.getProtocol())) {
                String path = url.getPath(); // "file:....!/..."
                int exclm = path.lastIndexOf('!');
                String jarPath = path.substring(5, exclm); // remove "file:"
                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.getName().startsWith(rootResourceName))
                        continue;
                    if (filter.accept(entry)) {
                        processor.process(jarFile, entry);
                    }
                }
            } else if ("file".equals(url.getProtocol())) {
                String path = url.getPath();
                File file = new File(path);
                scanFileResources(file, rootResourceName, processor);
            }
        }
    }

    void scanFileResources(File file, String resourceName, IFileOrEntryProcessor processor) {
        if (!resourceName.isEmpty() && !resourceName.endsWith("/"))
            resourceName += "/";
        for (File childFile : file.listFiles(filter)) {
            String childResourceName = resourceName + childFile.getName();
            if (childFile.isDirectory()) {
                scanFileResources(childFile, childResourceName, processor);
            } else {
                processor.process(childFile, childResourceName);
            }
        }
    }

}
