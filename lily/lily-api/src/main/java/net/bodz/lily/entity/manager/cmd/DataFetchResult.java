package net.bodz.lily.entity.manager.cmd;

import java.util.List;

public class DataFetchResult {

    public List<?> range;
    public long count;

    public DataFetchResult() {
    }

    public DataFetchResult(List<?> range, long count) {
        this.range = range;
        this.count = count;
    }

    public static DataFetchResult of(List<?> range, long count) {
        return new DataFetchResult(range, count);
    }

}
