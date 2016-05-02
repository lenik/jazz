package net.bodz.bas.db.ibatis.sql;

public class Pagination {

    Long offset;
    Long limit;

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public void setOffsetLimit(long offset, long limit) {
        this.offset = offset;
        this.limit = limit;
    }

}
