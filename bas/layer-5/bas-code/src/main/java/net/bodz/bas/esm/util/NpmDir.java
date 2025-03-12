package net.bodz.bas.esm.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.json.JsonObject;

public class NpmDir {

    static final String PACKAGE_JSON = "package.json";

    private Path packageFile;
    private Path baseDir;

    public NpmDir(Path baseDir) {
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
        this.packageFile = baseDir.resolve(PACKAGE_JSON);
    }

    public String getName() {
        String name = baseDir.getFileName().toString();
        return name;
    }

    public Path getBaseDir() {
        return baseDir;
    }

    public Path getPackageFile() {
        return packageFile;
    }

    public Path find(String name) {
        return baseDir.resolve(name);
    }

    public Path getPreferredPackageDir(String packageName) {
        String dir = packageName.replace('.', '/');
        return baseDir.resolve("src/" + dir);
    }

    public JsonObject resolve()
            throws ParseException, IOException {
        if (!Files.isRegularFile(packageFile))
            return null;
        String json = ResFn.path(packageFile).read().readString();
        return JsonFn.parseObject(json);
    }

    public static NpmDir closest(String child) {
        return closest(Paths.get(child));
    }

    public static NpmDir closest(Path child) {
        while (child != null) {
            if (isPackageDir(child))
                return new NpmDir(child);
            child = child.getParent();
        }
        return null;
    }

    public static boolean isPackageDir(Path dir) {
        Path packageJson = dir.resolve(PACKAGE_JSON);
        if (Files.isRegularFile(packageJson))
            return true;
        return false;
    }

    public static boolean isPackageDir(File dir) {
        File packageJson = new File(dir, PACKAGE_JSON);
        if (packageJson.exists())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((baseDir == null) ? 0 : baseDir.hashCode());
        result = prime * result + ((packageFile == null) ? 0 : packageFile.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NpmDir other = (NpmDir) obj;
        if (!Nullables.equals(baseDir, other.baseDir))
            return false;
        if (!Nullables.equals(packageFile, other.packageFile))
            return false;
        return true;
    }

}
