package net.bodz.bas.t.catalog;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IJavaQName {

    QualifiedName getJavaQName();

    default String getJavaPackage() {
        QualifiedName qName = getJavaQName();
        return qName == null ? null : qName.packageName;
    }

    default String getJavaName() {
        QualifiedName qName = getJavaQName();
        return qName == null ? null : qName.name;
    }

}
