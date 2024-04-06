package net.bodz.bas.esm;

import net.bodz.bas.t.tuple.QualifiedName;

public interface ITsImporter {

    EsmSource findSource(QualifiedName qName);

    EsmSource findSource(QualifiedName qName, String extension);

    default String importName(Class<?> clazz) {
        return importName(clazz.getName());
    }

    String importName(QualifiedName name);

    default String importName(String fullName) {
        return importName(QualifiedName.parse(fullName));
    }

    EsmName resolveName(QualifiedName qName, String alias, boolean type);

    String importName(EsmName name);

    default String importDefault(Class<?> clazz) {
        return importDefault(QualifiedName.of(clazz));
    }

    default String importDefault(String fullName) {
        return importDefault(QualifiedName.parse(fullName));
    }

    default String importDefault(QualifiedName qName) {
        return importDefaultAs(qName, qName.name);
    }

    String importDefaultAs(QualifiedName qName, String alias);

    default String importDefaultType(Class<?> clazz) {
        return importDefaultType(QualifiedName.of(clazz));
    }

    default String importDefaultType(String fullName) {
        return importDefaultType(QualifiedName.parse(fullName));
    }

    default String importDefaultType(QualifiedName qName) {
        return importDefaultTypeAs(qName, qName.name);
    }

    String importDefaultTypeAs(QualifiedName qName, String alias);

}
