package net.bodz.bas.io.res;

import java.io.File;
import java.net.URI;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.bodz.bas.meta.decl.NotNull;

public class DirSubpathItem
        implements
            IContainerItem {

    File baseDir;
    String subPath;

    public DirSubpathItem(File baseDir, String subPath) {
        this.baseDir = baseDir;
        this.subPath = subPath;
    }

    @Override
    public boolean isLocalFileItem() {
        return true;
    }

    @Override
    public boolean isJarItem() {
        return false;
    }

    @Override
    public File getContainerFile() {
        return baseDir;
    }

    @Override
    public JarFile getContainerJar() {
        return null;
    }

    @NotNull
    @Override
    public String getContainerPath() {
        return baseDir == null ? null : baseDir.getPath();
    }

    @Override
    public URI getContainerURI() {
        return baseDir == null ? null : baseDir.toURI();
    }

    @Override
    public File getItemFile() {
        File itemFile;
        if (baseDir == null) {
            if (subPath == null)
                return null;
            else
                itemFile = new File(subPath);
        } else {
            if (subPath == null)
                itemFile = baseDir;
            else
                itemFile = new File(baseDir, subPath);
        }
        return itemFile;
    }

    @Override
    public JarEntry getItemEntry() {
        return null;
    }

    @NotNull
    @Override
    public String getItemPath() {
        return subPath;
    }

    @Override
    public URI getItemURI() {
        File itemFile = getItemFile();
        if (itemFile == null)
            return null;
        else
            return itemFile.toURI();
    }

    @Override
    public String toString() {
        return baseDir + " :: " + subPath;
    }

}
