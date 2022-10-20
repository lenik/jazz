package net.bodz.bas.t.catalog;

public interface IJavaName {

    String getJavaName();

    String getJavaPackage();

    default String getJavaQName() {
        String name = getJavaName();
        if (name == null)
            return null;
        String packageName = getJavaPackage();
        if (packageName != null)
            name = packageName + "." + name;
        return name;
    }

}
