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

    List<T> filter(@Param("c") ICriterion criterion, @Param("opt") SelectOptions options);

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
    long deleteFor(@Param("c") ICriterion criterion);

    long count(@Param("c") ICriterion criterion);

    default List<T> filter(@Param("c") ICriterion criterion) {
        return filter(criterion, SelectOptions.ALL);
    }

    default <K> Map<K, T> filterMap(Function<T, K> keyf, ICriterion criterion) {
        return filterMap(keyf, criterion, SelectOptions.ALL);
    }

    default <K> Map<K, T> filterMap(Function<T, K> keyf, ICriterion criterion, SelectOptions options) {
        Map<K, T> map = new HashMap<>();
        for (T row : filter(criterion, options)) {
            K k = keyf.apply(row);
            map.put(k, row);
        }
        return map;
    }

}
