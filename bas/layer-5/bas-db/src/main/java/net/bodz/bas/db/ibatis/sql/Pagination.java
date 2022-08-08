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

    public long getPageIndex() {
        if (limit == 0)
            return 0;
        else
            return offset / limit;
    }

    public void setPageIndex(long pageIndex) {
        offset = pageIndex * limit;
    }

    public long getPageNumber() {
        return getPageIndex() + 1;
    }

    public void setPageNumber(long pageNumber) {
        setPageIndex(pageNumber - 1);
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
