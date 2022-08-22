package net.bodz.bas.codegen;

@FunctionalInterface
public interface ImportSetVisitor {

    void item(String name, boolean excluded);

    default void separator() {
    }

}
