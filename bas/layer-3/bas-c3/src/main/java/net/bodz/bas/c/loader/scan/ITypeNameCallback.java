package net.bodz.bas.c.loader.scan;

@FunctionalInterface
public interface ITypeNameCallback {

    /**
     * @return <code>true</code> to continue, <code>false</code> to cancel.
     */
    boolean typeName(String fqcn);

}
