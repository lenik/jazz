package net.bodz.bas.codegen;

@FunctionalInterface
public interface ImportSetVisitor {

    int item(String name, boolean excluded);

    default int separator() {
        return 0;
    }

}
