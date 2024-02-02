package net.bodz.bas.codegen;

@FunctionalInterface
public interface JavaImportsVisitor {

    int item(String name, boolean excluded);

    default int separator() {
        return 0;
    }

}
