package net.bodz.bas.db.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public interface IMapperTemplate<T, M>
        extends IMapper {

    List<T> all();

    List<T> all(@Param("opt") SelectOptions opt);

    List<T> filter(@Param("m") M mask);

    List<T> filter(@Param("m") M mask, @Param("opt") SelectOptions opt);

    T select(@Param("id") Object id);

    T selectByCodeName(@Param("code") String codeName);

    T selectPrev(Object id);

    T selectNext(Object id);

    /**
     * @return Number of records.
     */
    long insert(T obj);

    /**
     * @return Number of records.
     */
    long insertWithId(T obj);

    long update(T obj);

    boolean delete(@Param("id") Object id);

    int deleteFor(@Param("m") M mask);

    Map<String, Number> count(@Param("m") M mask);

    @Commit
    @Flush
    void flush();

}
