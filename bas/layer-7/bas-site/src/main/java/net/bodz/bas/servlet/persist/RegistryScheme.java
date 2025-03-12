package net.bodz.bas.servlet.persist;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.meta.decl.NotNull;

public class RegistryScheme {

    static Path rootDir;

    String id;
    Path homeDir;
    String extension = ".json";

    public RegistryScheme(@NotNull Path homeDir) {
        this.homeDir = homeDir;
    }

    RegistryScheme(String id) {
        if (id == null)
            throw new NullPointerException("id");
        this.id = id;
    }

    public Path getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(Path homeDir) {
        this.homeDir = homeDir;
    }

    public Path getContextFile() {
        checkInit();
        Path file = homeDir.resolve("context" + extension);
        return prepare(file);
    }

    public Path getVhostFile(String vhostId) {
        checkInit();
        Path file = homeDir.resolve("vhost/" + vhostId + extension);
        return prepare(file);
    }

    public Path getSessionFile(String sessionId) {
        checkInit();
        Path file = homeDir.resolve("session/" + sessionId + extension);
        return prepare(file);
    }

    public Path getRequestFile(String requestId) {
        checkInit();
        Path file = homeDir.resolve("request/" + requestId + extension);
        return prepare(file);
    }

    synchronized void checkInit() {
        if (homeDir == null) {
            if (rootDir != null)
                homeDir = rootDir.resolve(id);
            else
                throw new IllegalStateException(String.format(//
                        "Home dir of appRegistry %s isn't initialized.", id));
        }
    }

    Path prepare(Path file) {
        // file = file.getAbsoluteFile();
        Path dir = file.getParent();
        FileFn.mkdirs(dir);
        return file;
    }

    static void useDefaultRootDir() {
        Path home = SysProps.userHome;
        rootDir = home.resolve(".config/bas-site/registry");
    }

    static Map<String, RegistryScheme> map = new HashMap<>();

    @Deprecated
    public static synchronized RegistryScheme getInstance(String id) {
        return map.computeIfAbsent(id, RegistryScheme::new);
    }

}
