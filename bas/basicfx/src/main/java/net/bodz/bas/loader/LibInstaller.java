package net.bodz.bas.loader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;

public class LibInstaller {

    static boolean ignoreCase;
    static String  libSuffix;
    static {
        String osName = System.getProperty("os.name"); //$NON-NLS-1$
        if (osName != null) {
            if (osName.startsWith("Windows")) //$NON-NLS-1$
                ignoreCase = true;
        }
        libSuffix = System.mapLibraryName("name").substring(4); //$NON-NLS-1$
        if (ignoreCase)
            libSuffix = libSuffix.toLowerCase();
    }

    private File   installDir;

    public LibInstaller(File installDir) {
        this.installDir = installDir;
        init();
    }

    private static File deflDir;
    static {
        deflDir = new File(Files.getTmpDir(), "BundledLib"); //$NON-NLS-1$
        deflDir.mkdirs();
    }

    public LibInstaller() {
        this(deflDir);
    }

    // libname -> libfile
    private TextMap<File> installedLibraries;

    private void init() {
        installedLibraries = new HashTextMap<File>();
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
        String filename = System.mapLibraryName(libname);

        // the libfile must belong to this class loader
        // XXX - any parents loader?
        URL url = loader.getResource(filename);
        if (url == null)
            return null;

        File libfile = installedLibraries.get(libname);
        // TODO - how to get url's timestamp?
        if (libfile == null) {
            libfile = new File(installDir, filename);
            try {
                InputStream libdata = url.openStream();
                Files.copy(libdata, libfile);
                libdata.close();
                installedLibraries.put(libname, libfile);
            } catch (IOException e) {
                throw new Error(e.getMessage(), e);
            } finally {
                // libfile.deleteOnExit();
            }
        }
        return libfile.getAbsolutePath();
    }

}
