package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.IterableX;

public interface ResRecords<T>
        extends ImmediateIterableX<T, IOException>, IterableX<T, IOException> {

}
