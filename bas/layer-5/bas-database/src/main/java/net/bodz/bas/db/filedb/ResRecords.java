package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.util.iter.Mitablex;

public interface ResRecords<T>
        extends Mitablex<T, IOException>, Iterable<T> {

}
