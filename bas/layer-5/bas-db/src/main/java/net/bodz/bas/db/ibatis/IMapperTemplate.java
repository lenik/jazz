package net.bodz.bas.db.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.sql.SelectOptions;

public interface IMapperTemplate<T, M>
        extends IMapper {

    List<T> all();

    List<T> all(@Param("opt") SelectOptions opt);

    List<T> filter(@Param("m") M mask);

    List<T> filter(@Param("m") M mask, @Param("opt") SelectOptions opt);

    T select(Object id);

    T selectByCodeName(String codeName);

    T selectPrev(Object id);

    T selectNext(Object id);

    void _insert(T obj);

    long insert(T obj);

    void update(T obj);

    boolean delete(Object id);

    Map<String, Number> count(M mask);

    @Commit
    @Flush
    void flush();

}
