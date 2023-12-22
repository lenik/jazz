package net.bodz.bas.db.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.Helper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.NotImplementedException;

public interface ICommonDataOps<T, M> {

    default List<T> all() {
        return all(SelectOptions.ALL);
    }

    List<T> all(@Param("opt") SelectOptions opt);

    List<T> filter(@Param("m") M mask, @Param("opt") SelectOptions opt);

    /**
     * The generated id value will be saved in the object id property.
     *
     * @return number of records inserted.
     */
    long insert(T obj);

    int deleteObject(Object obj);

    /**
     * @return number of records deleted.
     */
    long deleteFor(@Param("m") M mask);

    long count(@Param("m") M mask);

    @Helper
    default M mask() {
        throw new NotImplementedException();
    }

    default List<T> filter(M mask) {
        return filter(mask, SelectOptions.ALL);
    }

    default <K> Map<K, T> filterMap(Function<T, K> keyf, M mask) {
        return filterMap(keyf, mask, SelectOptions.ALL);
    }

    default <K> Map<K, T> filterMap(Function<T, K> keyf, M mask, SelectOptions opt) {
        Map<K, T> map = new HashMap<>();
        for (T row : filter(mask, opt)) {
            K k = keyf.apply(row);
            map.put(k, row);
        }
        return map;
    }

}
