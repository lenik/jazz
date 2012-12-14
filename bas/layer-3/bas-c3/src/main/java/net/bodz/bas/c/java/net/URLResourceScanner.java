package net.bodz.bas.c.java.net;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;

public class URLResourceScanner {

    boolean recursive;
    boolean keepOrder = true;

    boolean includeStart = false;
    boolean dotForStart = false;
    boolean includeDirectories = true;

    public URLResourceScanner(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean isKeepOrder() {
        return keepOrder;
    }

    public void setKeepOrder(boolean keepOrder) {
        this.keepOrder = keepOrder;
    }

    public boolean isIncludeStart() {
        return includeStart;
    }

    public void setIncludeStart(boolean includeStart) {
        this.includeStart = includeStart;
    }

    public boolean isDotForStart() {
        return dotForStart;
    }

    public void setDotForStart(boolean dotForStart) {
        this.dotForStart = dotForStart;
    }

    public boolean isIncludeDirectories() {
        return includeDirectories;
    }

    public void setIncludeDirectories(boolean includeDirectories) {
        this.includeDirectories = includeDirectories;
    }

    public Map<String, URL> scan(URL start)
            throws IOException {

        Map<String, URL> map;
        if (keepOrder)
            map = new LinkedHashMap<String, URL>();
        else
            map = new TreeMap<String, URL>();

        String relativePath = dotForStart ? "." : "";

        switch (start.getProtocol()) {
        case "file":
            try {
                File startFile = new File(start.toURI());
                scanFiles(map, relativePath, startFile, 0);
            } catch (URISyntaxException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
            break;

        case "zip":
        case "jar":
            String _start = start.toString();
            String _zipURI = StringPart.beforeLast(_start, '!');
            _zipURI = StringPart.after(_zipURI, "jar:");

            String startEntryName = StringPart.afterLast(_start, "!/");

            URI zipURI;
            try {
                zipURI = new URI(_zipURI);
            } catch (URISyntaxException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }

            try (ZipFile zipFile = new ZipFile(new File(zipURI))) {
                scanZipEntries(map, relativePath, start, zipFile, startEntryName);
            } catch (MalformedURLException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
            break;
        }

        return map;
    }

    void scanFiles(Map<String, URL> map, String relativePath, File file, int depth) {
        if (includeStart || depth != 0) {
            if (includeDirectories || !file.isDirectory()) {
                URL fileUrl;
                try {
                    fileUrl = file.toURI().toURL();
                } catch (MalformedURLException e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
                map.put(relativePath, fileUrl);
            }
        }

        if (file.isDirectory())
            if (depth == 0 || recursive) {
                depth++;

                for (File childFile : file.listFiles()) {

                    String childPath = childFile.getName();
                    if (!relativePath.isEmpty())
                        childPath = relativePath + "/" + childPath;

                    scanFiles(map, childPath, childFile, depth);
                }
            }
    }

    void scanZipEntries(Map<String, URL> map, String relativePath, URL context, ZipFile zipFile, String startEntryName)
            throws MalformedURLException {
        int startLen = startEntryName.length();

        if (startEntryName.endsWith("/"))
            startEntryName = startEntryName.substring(0, --startLen);

        URL start = context;
        if (!context.getFile().endsWith("/"))
            context = new URL(context.toString() + "/");

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();

            String entryName = entry.getName();

            if (!includeDirectories)
                if (entryName.endsWith("/"))
                    continue;

            if (!entryName.startsWith(startEntryName))
                continue;

            if (entryName.length() == startLen) {
                if (includeStart)
                    map.put(relativePath, start);
                continue;
            }

            if (entryName.charAt(startLen) != '/')
                continue;

            String _childName = entryName.substring(startLen + 1);
            if (_childName.indexOf('/') != -1)
                if (!recursive)
                    continue;

            String childRelativePath;
            if (relativePath.isEmpty())
                childRelativePath = _childName;
            else
                childRelativePath = relativePath + '/' + _childName;

            URL childURL = new URL(context, _childName);
            map.put(childRelativePath, childURL);
        }
    }

}
