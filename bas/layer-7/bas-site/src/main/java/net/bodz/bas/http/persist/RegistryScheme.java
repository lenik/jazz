package net.bodz.bas.http.persist;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.system.SysProps;

public class RegistryScheme {

    static File rootDir;

    String id;
    File homeDir;
    String extension = ".json";

    public RegistryScheme(File homeDir) {
        if (homeDir == null)
        throw new NullPointerException("homeDir");
        this.homeDir=homeDir;
    }

    RegistryScheme(String id) {
        if (id == null)
            throw new NullPointerException("id");
        this.id = id;
    }

    public File getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(File homeDir) {
        this.homeDir = homeDir;
    }

    public File getContextFile() {
        checkInit();
        File file = new File(homeDir, "context" + extension);
        return prepare(file);
    }

    public File getVhostFile(String vhostId) {
        checkInit();
        File file = new File(homeDir, "vhost/" + vhostId + extension);
        return prepare(file);
    }

    public File getSessionFile(String sessionId) {
        checkInit();
        File file = new File(homeDir, "session/" + sessionId + extension);
        return prepare(file);
    }

    public File getRequestFile(String requestId) {
        checkInit();
        File file = new File(homeDir, "request/" + requestId + extension);
        return prepare(file);
    }

    synchronized void checkInit() {
        if (homeDir == null) {
            if (rootDir != null)
                homeDir = new File(rootDir + "/" + id);
            else
                throw new IllegalStateException(String.format(//
                        "Home dir of appRegistry %s isn't initialized.", id));
        }
    }

    File prepare(File file) {
        // file = file.getAbsoluteFile();
        File dir = file.getParentFile();
        dir.mkdirs();
        return file;
    }

    static void useDefaultRootDir() {
        rootDir = new File(SysProps.userHome, ".config/bas-site/registry");
    }

    static Map<String, RegistryScheme> map = new HashMap<>();

    @Deprecated
    public static synchronized RegistryScheme getInstance(String id) {
        RegistryScheme registry = map.get(id);
        if (registry == null) {
            registry = new RegistryScheme(id);
            map.put(id, registry);
        }
        return registry;
    }

}
