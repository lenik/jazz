package net.bodz.bas.c.loader.scan;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.err.UnexpectedException;

public class JarItem
        implements
            IResourceItem {

    ZipFile file;
    ZipEntry entry;

    public JarItem(ZipFile file, ZipEntry entry) {
        this.file = file;
        this.entry = entry;
    }

//    @Override
//    public String getName() {
//        return entry.getName();
//    }

    @Override
    public String getRelativePath() {
        return entry.getName();
    }

    @Override
    public URI toURI() {
        String zipPath = file.getName();
        String str = "jar:file:" + zipPath + "!/" + entry.getName();
        return URI.create(str);
    }

    @Override
    public URL toURL() {
        try {
            return toURI().toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return file + " :: " + entry;
    }

}
