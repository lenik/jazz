package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.err.UnexpectedException;

public abstract class AbstractFileOrEntryProcessor
        implements IFileOrEntryProcessor {

    protected URL toResource(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    protected URL toResource(ZipFile file, ZipEntry entry) {
        String zipPath = file.getName();
        String url = "jar:file:" + zipPath + "!/" + entry.getName();
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Can't parse URL", e);
        }
    }

}
