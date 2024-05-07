package net.bodz.bas.db.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.lily.criterion.ICriterion;

public interface ICommonDataOps<T> {

    default List<T> all() {
        return all(SelectOptions.ALL);
    }

    List<T> all(@Param("opt") SelectOptions opt);

    List<T> filter(@Param("c") ICriterion criteria, @Param("opt") SelectOptions options);

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
    long deleteFor(@Param("c") ICriterion criteria);

    long count(@Param("c") ICriterion criteria, @Param("opt") SelectOptions options);

    default long count(@Param("c") ICriterion criteria) {
        return count(criteria, SelectOptions.ALL);
    }

    default List<T> filter(@Param("c") ICriterion criteria) {
        return filter(criteria, SelectOptions.ALL);
    }

    default <K> Map<K, T> filterMap(Function<T, K> keyf, ICriterion criteria) {
        return filterMap(keyf, criteria, SelectOptions.ALL);
    }

    default <K> Map<K, T> filterMap(Function<T, K> keyf, ICriterion criteria, SelectOptions options) {
        Map<K, T> map = new HashMap<>();
        for (T row : filter(criteria, options)) {
            K k = keyf.apply(row);
            map.put(k, row);
        }
        return map;
    }

}
