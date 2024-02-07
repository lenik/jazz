package net.bodz.bas.codegen;

import java.io.File;
import java.util.Objects;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.t.tuple.Split;

public class QualifiedName {

    public final String packageName;
    public final String name;

    static final String CWD = ".";
    static final String FS = SysProps.fileSep;

    public QualifiedName(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }

    public static QualifiedName of(Class<?> clazz) {
        return new QualifiedName(clazz.getPackageName(), clazz.getSimpleName());
    }

    public static QualifiedName parse(String fullName) {
        Split split = Split.packageName(fullName);
        return new QualifiedName(split.a, split.b);
    }

    public QualifiedName join(String relativeName) {
        if (relativeName == null)
            throw new NullPointerException("relativeName");
        Split split = Split.packageName(relativeName);
        String pkg = packageName;
        if (split.a != null)
            if (pkg == null)
                pkg = split.a;
            else
                pkg += "." + split.a;
        return new QualifiedName(pkg, split.b);
    }

    public QualifiedName packageName(String packageName) {
        return new QualifiedName(packageName, name);
    }

    public QualifiedName name(String name) {
        return new QualifiedName(packageName, name);
    }

    public String getFullName() {
        if (packageName == null)
            return name;
        else
            return packageName + "." + name;
    }

    public String getDirName() {
        if (packageName == null)
            return CWD;
        else
            return packageName.replace(".", FS);
    }

    public String getBaseName(String extension) {
        if (extension == null)
            return name;
        else
            return name + "." + extension;
    }

    public String getLocalPath() {
        return getLocalPath(null);
    }

    public String getLocalPath(String extension) {
        String baseName = getBaseName(extension);
        if (packageName == null)
            return baseName;
        else
            return packageName.replace(".", FS) + FS + baseName;
    }

    public File getPackageDir(File baseDir) {
        if (baseDir == null)
            return new File(getDirName());
        else
            return new File(baseDir, getDirName());
    }

    public File toFile(File baseDir, String extension) {
        return new File(getPackageDir(baseDir), getBaseName(extension));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, packageName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QualifiedName other = (QualifiedName) obj;
        return Objects.equals(name, other.name) && Objects.equals(packageName, other.packageName);
    }

    @Override
    public String toString() {
        return getFullName();
    }

}
