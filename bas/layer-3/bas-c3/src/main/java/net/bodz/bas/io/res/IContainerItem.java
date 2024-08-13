package net.bodz.bas.io.res;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.meta.decl.NotNull;

public interface IContainerItem {

    boolean isLocalFileItem();

    boolean isJarItem();

    URI getContainerURI();

    default URL getContainerURL()
            throws MalformedURLException {
        URI uri = getContainerURI();
        return uri == null ? null : uri.toURL();
    }

    File getContainerFile();

    JarFile getContainerJar();

    @NotNull
    String getContainerPath();

    default String getContainerName() {
        String path = getContainerPath();
        return path == null ? null : FilePath.getBaseName(path);
    }

    File getItemFile();

    JarEntry getItemEntry();

    /**
     * Item path never starts with /.
     */
    @NotNull
    String getItemPath();

    default String getItemName() {
        String path = getItemPath();
        return path == null ? null : FilePath.getBaseName(path);
    }

    URI getItemURI();

    default URL getItemURL()
            throws MalformedURLException {
        URI uri = getItemURI();
        return uri == null ? null : uri.toURL();
    }

}
