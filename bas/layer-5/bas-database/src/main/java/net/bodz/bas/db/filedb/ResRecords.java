package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.collection.iterator.IterableMX;
import net.bodz.bas.collection.iterator.IterableX;

public interface ResRecords<T>
        extends IterableMX<T, IOException>, IterableX<T, IOException> {

}
