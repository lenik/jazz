package net.bodz.bas.loader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamWriting;

public class LibInstaller {

    static boolean ignoreCase;
    static String libSuffix;
    static {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            if (osName.startsWith("Windows"))
                ignoreCase = true;
        }
        libSuffix = System.mapLibraryName("name").substring(4);
        if (ignoreCase)
            libSuffix = libSuffix.toLowerCase();
    }

    private File installDir;

    public LibInstaller(File installDir) {
        this.installDir = installDir;
        init();
    }

    private static File deflDir;
    static {
        deflDir = new File(TempFile.getTmpDir(), "BundledLib");
        deflDir.mkdirs();
    }

    public LibInstaller() {
        this(deflDir);
    }

    // libname -> libfile
    private Map<String, File> installedLibraries;

    private void init() {
        installedLibraries = new HashMap<String, File>();
        File[] files = installDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (ignoreCase)
                    name = name.toLowerCase();
                return name.endsWith(libSuffix);
            }
        });
        for (File file : files) {
            String name = file.getName();
            String libname = name.substring(0, name.length() - libSuffix.length());
            installedLibraries.put(libname, file);
        }
    }

    /**
     * @return <code>null</code> if not found.
     */
    public String findLibrary(ClassLoader loader, String libname) {
        String libFilename = System.mapLibraryName(libname);

        // the libfile must belong to this class loader
        // XXX - any parents loader?
        URL libURL = loader.getResource(libFilename);
        if (libURL == null)
            return null;

        File installedLibFile = installedLibraries.get(libname);
        // TODO - how to get url's timestamp?
        if (installedLibFile == null) {
            URLResource libResource = new URLResource(libURL);
            installedLibFile = new File(installDir, libFilename);
            try {
                new LocalFileResource(installedLibFile)//
                        .tooling()._for(StreamWriting.class).writeBytes(libResource);
                installedLibraries.put(libname, installedLibFile);
            } catch (IOException e) {
                throw new Error(e.getMessage(), e);
            } finally {
                // libfile.deleteOnExit();
            }
        }
        return installedLibFile.getAbsolutePath();
    }

}
