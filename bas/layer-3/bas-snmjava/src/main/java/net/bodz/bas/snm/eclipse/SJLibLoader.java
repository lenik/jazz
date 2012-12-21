package net.bodz.bas.snm.eclipse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.io.resource.builtin.FileResource;
import net.bodz.bas.io.resource.tools.StreamLoading;

public class SJLibLoader {

    public static class LibInfo {

        private File target;

        // private String[] depends;

        public LibInfo(File target) {
            assert target != null;
            this.target = target;
        }

        public File getTarget() {
            return target;
        }

        public void setTarget(File target) {
            this.target = target;
        }

    }

    /** library name -> properties */
    static class LibDir
            extends HashMap<String, LibInfo> {
        private static final long serialVersionUID = 388938369034212989L;
    }

    private List<String> findPaths;
    private Map<File, LibDir> libDirs;

    public SJLibLoader() {
        findPaths = new ArrayList<String>();
        libDirs = new HashMap<File, LibDir>();
    }

    public void addPath(String path) {
        findPaths.add(path);
    }

    public void addPaths(String paths) {
        String[] pathv = paths.split(SystemInfo.pathSeparator);
        for (String path : pathv) {
            findPaths.add(path);
        }
    }

    /**
     * @return null if not found
     */
    public File findFile(String filename) {
        for (String path : findPaths) {
            File canoni = FilePath.canoniOf(path);
            if (!canoni.isDirectory())
                continue;
            File file = new File(canoni, filename);
            if (file.exists())
                return file;
        }
        return null;
    }

    /**
     * @return null if not found
     */
    public LibInfo findLibrary(String name) {
        for (String path : findPaths) {
            File canoni = FilePath.canoniOf(path);
            if (!canoni.isDirectory())
                continue;

            LibDir libDir = libDirs.get(canoni);
            if (libDir == null) {
                libDir = loadLibDir(canoni);
                libDirs.put(canoni, libDir);
            }

            LibInfo libInfo = libDir.get(name);
            if (libInfo != null)
                return libInfo;
        }
        return null;
    }

    public File findLibraryFile(String name) {
        LibInfo lib = findLibrary(name);
        if (lib == null)
            return null;
        return lib.getTarget();
    }

    public static String librariesIni = "libraries.ini";

    protected LibDir loadLibDir(File dir) {
        assert dir.isDirectory();
        LibDir libDir = new LibDir();
        File iniFile = new File(dir, librariesIni);
        if (iniFile.canRead()) {
            Properties libraries;
            try {
                libraries = new FileResource(iniFile)//
                        .tooling()._for(StreamLoading.class).loadProperties();
            } catch (IOException e) {
                throw new Error("failed to load " + iniFile + ": " + e.getMessage(), e);
            }
            for (Entry<Object, Object> e : libraries.entrySet()) {
                String name = (String) e.getKey();
                if (libDir.containsKey(name))
                    throw new IllegalArgumentException("duplicated name: " + name + " defined in " + iniFile);

                String value = (String) e.getValue();

                File target = FilePath.canoniOf(dir, value);
                assert target.isFile() : "invalid target " + target;
                if (!target.isFile())
                    continue;

                LibInfo info = new LibInfo(target);
                libDir.put(name, info);
            }
        }
        return libDir;
    }

    public static final SJLibLoader DEFAULT;
    static {
        DEFAULT = new SJLibLoader();
        DEFAULT.addPath(".");
        DEFAULT.addPath("..");
        DEFAULT.addPath("../lib");
        String JAVA_LIB = System.getenv("JAVA_LIB");
        if (JAVA_LIB != null)
            DEFAULT.addPaths(JAVA_LIB);
    }

}