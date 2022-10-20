package net.bodz.bas.t.catalog;

public interface IMutableJavaName
        extends
            IJavaName {

    void setJavaName(String javaName);

    void setJavaPackage(String javaPackage);

    default void setJavaQName(String javaQName) {
        if (javaQName == null) {
            setJavaName(null);
            setJavaPackage(null);
        } else {
            int dot = javaQName.lastIndexOf('.');
            if (dot == -1) {
                setJavaPackage(null);
                setJavaName(javaQName);
            } else {
                setJavaPackage(javaQName.substring(0, dot));
                setJavaName(javaQName.substring(dot + 1));
            }
        }
    }

}
