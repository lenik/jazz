package net.bodz.bas.uber;

import net.bodz.bas.c.type.TypeExtensions;
import net.bodz.bas.meta.codegen.IndexedType;

public class UberTypeCollector {

    public static void main(String[] args)
            throws Exception {
        for (Class<?> indexedType : TypeExtensions.forClassWithAnnotation(IndexedType.class)) {
            System.out.println(indexedType);
        }
    }

}
