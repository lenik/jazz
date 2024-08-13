package net.bodz.bas.io.res;

import java.io.File;
import java.net.URI;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarEntryItem
        implements
            IContainerItem {

    JarFile jarFile;
    JarEntry entry;

    public JarEntryItem(JarFile jarFile, JarEntry entry) {
        String entryName = entry.getName();
        if (entryName.startsWith("/"))
            throw new IllegalArgumentException("JarEntry starts with /.");
        this.jarFile = jarFile;
        this.entry = entry;
    }

    @Override
    public boolean isLocalFileItem() {
        return false;
    }

    @Override
    public boolean isJarItem() {
        return true;
    }

    @Override
    public File getContainerFile() {
        String path = jarFile.getName();
        return new File(path);
    }

    @Override
    public JarFile getContainerJar() {
        return jarFile;
    }

    @Override
    public String getContainerPath() {
        return jarFile.getName();
    }

    @Override
    public URI getContainerURI() {
        return getContainerFile().toURI();
    }

    @Override
    public File getItemFile() {
        return null;
    }

    @Override
    public JarEntry getItemEntry() {
        return entry;
    }

    @Override
    public String getItemPath() {
        return entry.getName();
    }

    @Override
    public URI getItemURI() {
        String zipPath = jarFile.getName();
        String str = "jar:file:" + zipPath + "!/" + entry.getName();
        return URI.create(str);
    }

    @Override
    public String toString() {
        return jarFile + " :: " + entry;
    }

}
