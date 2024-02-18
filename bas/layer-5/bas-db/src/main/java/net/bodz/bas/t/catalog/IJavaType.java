package net.bodz.bas.t.catalog;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IJavaType {

    QualifiedName getJavaType();

    default String getJavaPackage() {
        return getJavaType().packageName;
    }

}
