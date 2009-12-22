package net.bodz.bas.api.types;

public interface Queryable {

    Object query(String id);

    <T> T query(Class<T> type);

}
