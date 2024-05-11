package net.bodz.bas.db.ibatis.sql;

public interface IPagination {

    long getPageIndex();

    long getPageNumber();

    long getPageSize();

}