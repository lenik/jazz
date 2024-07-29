package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ResourceScanner {

    static final Logger logger = LoggerFactory.getLogger(ResourceScanner.class);

    IScanOptions options;
//    IFileOrEntryProcessor processor;

    public ResourceScanner(IScanOptions options) {
        this.options = options;
    }

    public ResList scanResources(ClassLoader classLoader, String resourceName)
            throws IOException {
        ResList list = new ResList();
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();

            JarFile jarFile = JarFiles.fromJarURL(url);
            if (jarFile != null) {
                if (! options.acceptJarPath(jarFile))
                    continue;

                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (! entry.getName().startsWith(resourceName))
                        continue;
                    if (options.acceptZipEntry(entry)) {
                        list.addItem(new JarItem(jarFile, entry));
                    }
                }
            }

            else if ("file".equals(url.getProtocol())) {
                URI uri;
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
                File file = new File(uri);
                if (options.acceptPath(file))
                    scanLocalDir(list, file, resourceName);
                else
                    logger.debug("excluded: " + url);
            }
        }
        return list;
    }

    void scanLocalDir(ResList list, File dir, String resourcePath) {
//        logger.info("Scan-LocalDir: ", dir);
        scanLocalTree(list, dir, resourcePath);
    }

    void scanLocalTree(ResList list, File dir, String resourcePath) {
        String resourcePath_ = resourcePath;
        if (! resourcePath_.isEmpty())
            resourcePath_ += "/";

        for (File child : dir.listFiles(options.getFileFilter())) {
            String name = child.getName();
            String childPath = resourcePath_ + name;
            if (child.isDirectory()) {
                if (options.acceptDirEntry(child))
                    scanLocalTree(list, child, childPath);
            } else {
                if (options.acceptFileEntry(child))
                    list.addItem(new FileItem(child, childPath));
            }
        }
    }

}
