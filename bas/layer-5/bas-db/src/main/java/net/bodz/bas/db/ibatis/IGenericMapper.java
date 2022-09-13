package net.bodz.bas.db.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public interface IGenericMapper<T, M>
        extends
            IMapper {

    default List<T> all() {
        return all(SelectOptions.ALL);
    }

    List<T> all(@Param("opt") SelectOptions opt);

    List<T> filter(@Param("m") M mask, @Param("opt") SelectOptions opt);

    T select(@Param("id") Object id);

    /**
     * The generated id value will be saved in the object id property.
     *
     * @return Number of records.
     */
    long insert(T obj);

    /**
     * Insert with custom specified id value.
     *
     * @return Number of records.
     */
    long insertWithId(T obj);

    long update(T obj);

    boolean delete(@Param("id") Object id);

    int deleteFor(@Param("m") M mask);

    long count(@Param("m") M mask);

    @Commit
    @Flush
    void flush();

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
