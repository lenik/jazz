package net.bodz.bas.lang;

public interface IQueryable {

    Object query(String id);

    <T> T query(Class<T> type);

}
