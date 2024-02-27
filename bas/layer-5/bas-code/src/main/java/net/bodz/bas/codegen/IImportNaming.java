package net.bodz.bas.codegen;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IImportNaming {

    default String importName(Class<?> clazz) {
        return importName(clazz.getName());
    }

    String importName(QualifiedName name);

    default String importName(String fullName) {
        return importName(QualifiedName.parse(fullName));
    }

}
