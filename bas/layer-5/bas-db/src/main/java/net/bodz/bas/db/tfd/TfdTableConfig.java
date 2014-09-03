package net.bodz.bas.db.tfd;

public interface TfdTableConfig {

    int getBatchSize();

    String getFileName(Object obj);

    Iterable<Object> loadBatchWith(Object start);

}
