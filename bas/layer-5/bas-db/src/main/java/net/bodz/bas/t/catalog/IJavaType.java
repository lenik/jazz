package net.bodz.bas.t.catalog;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.t.tuple.QualifiedName;

public interface IJavaType {

    QualifiedName getJavaType();

    @Derived
    default String getJavaTypeName() {
        QualifiedName javaType = getJavaType();
        if (javaType == null)
            return null;
        else
            return javaType.getFullName();
    }

    @Derived
    default String getJavaPackageName() {
        return getJavaType().packageName;
    }

}
