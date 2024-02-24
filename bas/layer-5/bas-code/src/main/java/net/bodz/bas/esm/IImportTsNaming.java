package net.bodz.bas.esm;

import net.bodz.bas.codegen.IImportNaming;
import net.bodz.bas.t.tuple.QualifiedName;

public interface IImportTsNaming
        extends
            IImportNaming {

    String importName(EsmName name);

    default String importDefaultAs(Class<?> clazz) {
        return importDefaultAs(QualifiedName.of(clazz));
    }

    default String importDefaultAs(Class<?> clazz, String alias) {
        return importDefaultAs(QualifiedName.of(clazz), alias);
    }

    default String importDefaultAs(String fullName) {
        return importDefaultAs(QualifiedName.parse(fullName));
    }

    default String importDefaultAs(String fullName, String alias) {
        return importDefaultAs(QualifiedName.parse(fullName), alias);
    }

    default String importDefaultAs(QualifiedName qName) {
        return importDefaultAs(qName, qName.name);
    }

    String importDefaultAs(QualifiedName qName, String alias);

}
