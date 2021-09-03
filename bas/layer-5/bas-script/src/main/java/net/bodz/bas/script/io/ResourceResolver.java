package net.bodz.bas.script.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ResourceResolver {

    static final Logger logger = LoggerFactory.getLogger(ResourceResolver.class);

    static final String fileSep = SystemProperties.getFileSeparator();

    File pkgdatadir = new File("/usr/share/catme");
    File configDir;

    public boolean searchWorkDir = false;
    File workDir;
    public boolean searchHomeDir = false;
    File homeDir;

    private boolean searchClassResources;
    public List<Class<?>> searchFromClasses = new ArrayList<>();
    public List<ClassLoader> searchFromClassLoaders = new ArrayList<>();

    public boolean searchPomDir = true;
    MavenPomDir pomDir = MavenPomDir.fromClass(getClass());

    List<File> libDirs;
    public boolean searchLibDirs = true;
    public boolean searchLibDirsForExtension;

    boolean searchEnvLangLIBs;
    public boolean searchEnvLIB;
    Function<String, ?> extensionToName;

    FileSearcher bareNameSearcher;
    Map<String, FileSearcher> byExtension = new HashMap<>();

    public ResourceResolver() {
        String cwd = SystemProperties.getUserDir();
        if (cwd != null)
            workDir = new File(cwd);

        String home = SystemProperties.getUserHome();
        if (home != null)
            homeDir = new File(home);

        if (home == null)
            home = cwd;

        this.configDir = new File(home, ".config" + fileSep + "catme");
    }

    public void searchFrom(Class<?>... classes) {
        searchClassResources = true;
        for (Class<?> c : classes)
            searchFromClasses.add(c);
    }

    public void searchFrom(ClassLoader... loaders) {
        searchClassResources = true;
        for (ClassLoader loader : loaders)
            searchFromClassLoaders.add(loader);
    }

    public void searchEnvLangLibs(Function<String, ?> extensionToName) {
        if (extensionToName == null)
            throw new NullPointerException("map");
        searchEnvLangLIBs = true;
        this.extensionToName = extensionToName;
    }

    public synchronized FileSearcher getFileSearcherForBareNames()
            throws IOException {
        if (bareNameSearcher == null)
            bareNameSearcher = buildFileSearcher(null);
        return bareNameSearcher;
    }

    public synchronized FileSearcher getFileSearcherForExtension(String extension)
            throws IOException {
        if (extension == null)
            throw new NullPointerException("extension");
        FileSearcher fileSearcher = byExtension.get(extension);
        if (fileSearcher == null) {
            fileSearcher = buildFileSearcher(extension);
            byExtension.put(extension, fileSearcher);
        }
        return fileSearcher;
    }

    FileSearcher buildFileSearcher(String extension)
            throws IOException {
        FileSearcher fileSearcher = new FileSearcher();

        if (searchWorkDir && workDir != null)
            fileSearcher.addSearchDir(workDir);

        if (searchHomeDir && homeDir != null)
            fileSearcher.addSearchDir(homeDir);

        if (searchPomDir)
            if (pomDir != null) {
                File mainResourceDir = pomDir.getResourceDir(getClass());
                fileSearcher.addSearchDir(mainResourceDir);
            }

        if (searchLibDirs)
            if (libDirs != null) {
                for (File dir : libDirs)
                    fileSearcher.addSearchDir(dir);
            }

        if (extension != null) {
            if (searchLibDirsForExtension) {
                File sysPathDir = new File(pkgdatadir, "path" + fileSep + extension);
                fileSearcher.addPathDir(sysPathDir);
                File userPathDir = new File(configDir, "path" + fileSep + extension);
                fileSearcher.addPathDir(userPathDir);
            }

            if (searchEnvLangLIBs) {
                Object any = extensionToName.apply(extension);
                String name = any.toString();
                String langLibName = name.toUpperCase() + "LIB";
                String langLibPath = System.getenv(langLibName.toUpperCase());
                if (langLibPath != null)
                    fileSearcher.addPathEnv(langLibPath);
            }
        }

        if (searchEnvLIB) {
            String sysLibPath = System.getenv("LIB");
            if (sysLibPath != null)
                fileSearcher.addPathEnv(sysLibPath);
        }

        return fileSearcher;
    }

    public String loadTextResource(String filename)
            throws IOException {
        ResourceVariant res = findResource(filename);
        if (res == null) {
            logger.error("Can't find resource " + filename);
            return null;
        }
        String text = res.toResource().read().readString();
        return text;
    }

    public ResourceVariant findResource(String filename)
            throws IOException {
        if (filename.startsWith("/")) {
            File file = new File(filename);
            if (!file.exists())
                return null;
            return new ResourceVariant(file);
        }

        if (searchClassResources) {
            String name = filename;
            while (name.startsWith("./"))
                name = name.substring(2);

            for (Class<?> clazz : searchFromClasses) {
                logger.debugf("find %s from class %s", name, clazz.getName());
                URL url = clazz.getResource(name); // relative name
                if (url != null)
                    return new ResourceVariant(url);
            }

            for (ClassLoader loader : searchFromClassLoaders) {
                String loaderType = loader.getClass().getSimpleName();
                while (name.startsWith("/")) // absolute name.
                    name = name.substring(1);
                logger.debugf("find %s from %s %s", name, loaderType, loader);
                URL url = loader.getResource(name);
                if (url != null)
                    return new ResourceVariant(url);
            }
        }

        String extension = FilePath.getExtension(filename, false);
        FileSearcher fileSearcher;
        if (extension != null)
            fileSearcher = getFileSearcherForExtension(extension);
        else
            fileSearcher = getFileSearcherForBareNames();
        logger.debugf("find %s from file searcher %s", filename, fileSearcher);
        for (File file : fileSearcher.search(filename))
            return new ResourceVariant(file);

        logger.warn("failed to resolve resource " + filename);
        return null;
    }

}
