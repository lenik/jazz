package net.bodz.bas.db.ibatis.sql;

public class Pagination {

    long limit;
    long offset;

    public Pagination() {
    }

    public Pagination(long limit, long offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setLimitOffset(long limit, long offset) {
        this.limit = limit;
        this.offset = offset;
    }

}
