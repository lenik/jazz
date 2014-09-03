package net.bodz.bas.db.impl.tfd;

public interface TfdTableConfig {

    int getBatchSize();

    String getFileName(Object obj);

    Iterable<Object> loadBatchWith(Object start);

}
