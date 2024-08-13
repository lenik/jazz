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
import net.bodz.bas.io.res.ContainerItemList;
import net.bodz.bas.io.res.DirSubpathItem;
import net.bodz.bas.io.res.JarEntryItem;
import net.bodz.bas.io.res.JarFiles;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ResourceScanner {

    static final Logger logger = LoggerFactory.getLogger(ResourceScanner.class);

    IScanOptions options;
//    IFileOrEntryProcessor processor;

    public ResourceScanner(IScanOptions options) {
        this.options = options;
    }

    public ContainerItemList scanResources(ClassLoader classLoader, String resourceName)
            throws IOException {
        ContainerItemList list = new ContainerItemList();
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();

            File file = JarFiles._fromJarURL(url);
            if (file != null) {
                if (! options.acceptJarPath(file))
                    continue;

                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (! entry.getName().startsWith(resourceName))
                        continue;
                    if (options.acceptZipEntry(entry)) {
                        list.addItem(new JarEntryItem(jarFile, entry));
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
                File localDir = new File(uri);
                assert localDir.isDirectory();
                if (options.acceptDirPath(localDir))
                    scanLocalDir(list, localDir, resourceName);
                else
                    logger.debug("excluded: " + url);
            }
        }
        return list;
    }

    void scanLocalDir(ContainerItemList list, File dir, String resourcePath) {
//        logger.info("Scan-LocalDir: ", dir);
        scanLocalTree(dir, list, dir, resourcePath);
    }

    void scanLocalTree(File startDir, ContainerItemList list, File dir, String resourcePath) {
        String resourcePath_ = resourcePath;
        if (! resourcePath_.isEmpty())
            resourcePath_ += "/";

        File[] children = dir.listFiles(options.getFileFilter(startDir));
        for (File child : children) {
            String name = child.getName();
            String childPath = resourcePath_ + name;
            if (child.isDirectory())
                scanLocalTree(startDir, list, child, childPath);
            else
                list.addItem(new DirSubpathItem(child, childPath));
        }
    }

}
