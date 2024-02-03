package net.bodz.bas.codegen;

public interface IImportNaming {

    default String importName(Class<?> clazz) {
        return importName(clazz.getName());
    }

    String importName(QualifiedName name);

    String importName(String name);

}
