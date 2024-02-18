package net.bodz.bas.t.catalog;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IMutableJavaQName
        extends
            IJavaQName {

    void setJavaQName(QualifiedName qName);

    default void setJavaQName(String qName) {
        setJavaQName(QualifiedName.parse(qName));
    }

    default void setJavaQName(String packageName, String name) {
        setJavaQName(new QualifiedName(packageName, name));
    }

}
