package net.bodz.bas.commons;

public interface IQueryable {

    Object query(String id);

    <T> T query(Class<T> type);

}
