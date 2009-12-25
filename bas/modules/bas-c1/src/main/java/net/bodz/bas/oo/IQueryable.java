package net.bodz.bas.oo;

public interface IQueryable {

    Object query(String id);

    <T> T query(Class<T> type);

}
