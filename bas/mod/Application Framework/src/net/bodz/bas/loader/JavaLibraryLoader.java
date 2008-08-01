package net.bodz.bas.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import net.bodz.bas.io.Files;
import net.bodz.bas.sys.SystemInfo;

public class JavaLibraryLoader {

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
    static class LibDir extends HashMap<String, LibInfo> {
        private static final long serialVersionUID = 388938369034212989L;
    }

    private List<String>      findPaths;
    private Map<File, LibDir> libDirs;

    public JavaLibraryLoader() {
        findPaths = new ArrayList<String>();
        libDirs = new HashMap<File, LibDir>();

        String JAVA_LIB = System.getenv("JAVA_LIB");
        if (JAVA_LIB != null) {
            String pathSeparator = SystemInfo.pathSeparator;
            String[] paths = JAVA_LIB.split(pathSeparator);
            for (String path : paths) {
                findPaths.add(path);
            }
        }
        findPaths.add(".");
        findPaths.add("../lib");
    }

    public LibInfo findLibrary(String name) throws IOException {
        for (String path : findPaths) {
            File canoni = Files.canoniOf(path);
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

    public static String librariesIni = "libraries.ini";

    protected LibDir loadLibDir(File dir) throws IOException {
        assert dir.isDirectory();
        LibDir libDir = new LibDir();
        File ini = new File(dir, librariesIni);
        if (ini.canRead()) {
            Properties libraries = Files.loadProperties(ini);
            for (Entry<Object, Object> e : libraries.entrySet()) {
                String name = (String) e.getKey();
                if (libDir.containsKey(name))
                    throw new IllegalArgumentException("duplicated name: "
                            + name + " defined in " + ini);

                String value = (String) e.getValue();

                File target = Files.canoniOf(dir, value);
                assert target.isFile();
                if (!target.isFile())
                    continue;

                LibInfo info = new LibInfo(target);
                libDir.put(name, info);
            }
        }
        return libDir;
    }

}
