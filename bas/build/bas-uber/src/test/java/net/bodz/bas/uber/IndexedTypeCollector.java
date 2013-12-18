package net.bodz.bas.uber;

import java.io.IOException;

import net.bodz.bas.meta.codegen.IndexedType;

/**
 * @see EveryIndexedTypeCollector
 */
public class IndexedTypeCollector
        extends NetBodzTypeCollector<IndexedType> {

    public static void main(String[] args)
            throws IOException {
        new IndexedTypeCollector().collect();
    }

}
