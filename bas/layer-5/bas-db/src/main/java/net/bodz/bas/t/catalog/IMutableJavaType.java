package net.bodz.bas.t.catalog;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IMutableJavaType
        extends
            IJavaType {

    void setJavaType(QualifiedName javaType);

    default void setJavaType(String packageName, String simpleName) {
        setJavaType(new QualifiedName(packageName, simpleName));
    }

    default void setJavaType(String javaType) {
        QualifiedName qName;
        if (javaType == null)
            qName = null;
        else
            qName = QualifiedName.parse(javaType);
        setJavaType(qName);
    }

//    void setJavaName(String javaName);
//
//    void setJavaPackage(String javaPackage);
//
//    default void setJavaQName(String javaQName) {
//        if (javaQName == null) {
//            setJavaName(null);
//            setJavaPackage(null);
//        } else {
//            int dot = javaQName.lastIndexOf('.');
//            if (dot == -1) {
//                setJavaPackage(null);
//                setJavaName(javaQName);
//            } else {
//                setJavaPackage(javaQName.substring(0, dot));
//                setJavaName(javaQName.substring(dot + 1));
//            }
//        }
//    }

}
