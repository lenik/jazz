package net.bodz.bas.c.loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.java.io.TempFile;

public class NativeLibraryInstaller {

    private static boolean IGNORE_CASE;
    private static String LIB_EXTENSION;
    private static File DEFAULT_INSTALL_DIR;
    static {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            if (osName.startsWith("Windows"))
                IGNORE_CASE = true;
        }
        LIB_EXTENSION = System.mapLibraryName("a").substring(1);
        if (IGNORE_CASE)
            LIB_EXTENSION = LIB_EXTENSION.toLowerCase();

        DEFAULT_INSTALL_DIR = new File(TempFile.getTempRoot(), "BundledLib");
        DEFAULT_INSTALL_DIR.mkdirs();
    }

    private File installDir;
    private List<ClassLoader> loaders = new ArrayList<>();
    private Map<String, File> installedLibraries = new HashMap<>();

    public NativeLibraryInstaller() {
        this(DEFAULT_INSTALL_DIR);
    }

    public NativeLibraryInstaller(File installDir) {
        this.installDir = installDir;

        File[] existedFiles = installDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (IGNORE_CASE)
                    name = name.toLowerCase();
                return name.endsWith(LIB_EXTENSION);
            }
        });

        for (File file : existedFiles) {
            String name = file.getName();
            String existedLibname = name.substring(0, name.length() - LIB_EXTENSION.length());
            installedLibraries.put(existedLibname, file);
        }
    }

    public boolean addLoader(ClassLoader classLoader) {
        if (classLoader == null)
            throw new NullPointerException("classLoader");

        int existing = loaders.indexOf(classLoader);
        if (existing != -1)
            return false;

        loaders.add(classLoader);
        return true;
    }

    public void addParallelLoader() {
        addLoader(NativeLibraryInstaller.class.getClassLoader());
    }

    public void addDefaultLoaders() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null)
            addLoader(contextClassLoader);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        if (systemClassLoader != contextClassLoader)
            addLoader(systemClassLoader);
    }

    /**
     * Install the library to local file somewhere.
     * 
     * @return <code>null</code> if not found.
     * @throws IOException
     */
    public synchronized File install(String libname)
            throws IOException {
        String filename = System.mapLibraryName(libname);

        URL resource = null;
        for (ClassLoader loader : loaders) {
            resource = loader.getResource(filename);
            if (resource != null)
                break;
        }
        if (resource == null)
            return null;

        File installedFile = installedLibraries.get(libname);

        if (installedFile == null) {
            installedFile = new File(installDir, filename);

            try (InputStream in = resource.openStream(); //
                    OutputStream out = new FileOutputStream(installedFile)) {

                byte[] block = new byte[8192];
                int cb;
                while ((cb = in.read(block)) != -1) {
                    out.write(block, 0, cb);
                }
            }

            installedLibraries.put(libname, installedFile);
        }

        return installedFile;
    }

    static final NativeLibraryInstaller instance = new NativeLibraryInstaller();
    static {
        instance.addParallelLoader();
    }

    public static NativeLibraryInstaller getInstance() {
        return instance;
    }

}
