package net.bodz.bas.t.catalog;

public interface IJavaName {

    String getJavaName();

    default String getJavaQName() {
        return getJavaName();
    }

}
