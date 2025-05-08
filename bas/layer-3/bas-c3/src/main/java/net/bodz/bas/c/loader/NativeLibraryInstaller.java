package net.bodz.bas.c.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.java.util.stream.iterable.Stream;

public class NativeLibraryInstaller {

    private static boolean IGNORE_CASE;
    private static final String LIB_EXTENSION;
    private static final Path DEFAULT_INSTALL_DIR;

    static {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            if (osName.startsWith("Windows"))
                IGNORE_CASE = true;
        }
        String aName = System.mapLibraryName("a").substring(1);
        if (IGNORE_CASE)
            aName = aName.toLowerCase();
        LIB_EXTENSION = aName;

        DEFAULT_INSTALL_DIR = TempFile.getTempRoot().resolve("BundledLib");
        FileFn.mkdirs(DEFAULT_INSTALL_DIR);
    }

    final Path installDir;
    final List<ClassLoader> loaders = new ArrayList<>();
    final Map<String, File> installedLibraries = new HashMap<>();

    public NativeLibraryInstaller() {
        this(DEFAULT_INSTALL_DIR);
    }

    public NativeLibraryInstaller(Path installDir) {
        this.installDir = installDir;

        try (Stream<Path> stream = FileFn.list(installDir)) {
            for (Path child : stream) {
                String name = child.getFileName().toString();
                if (IGNORE_CASE)
                    name = name.toLowerCase();

                String existedLibname = name;

                if (LIB_EXTENSION != null) {
                    if (name.endsWith(LIB_EXTENSION))
                        existedLibname = name.substring(0, name.length() - LIB_EXTENSION.length());
                    else
                        continue;
                }
                installedLibraries.put(existedLibname, child.toFile());
            }
        } catch (IOException e) {
            // ignore.
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
            installedFile = installDir.resolve(filename).toFile();

            try (InputStream in = resource.openStream()) {
                try (OutputStream out = Files.newOutputStream(installedFile.toPath())) {
                    byte[] block = new byte[8192];
                    int cb;
                    while ((cb = in.read(block)) != -1) {
                        out.write(block, 0, cb);
                    }
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
