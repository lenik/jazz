package net.bodz.bas.api.types;

public interface IQueryable {

    Object query(String id);

    <T> T query(Class<T> type);

}
