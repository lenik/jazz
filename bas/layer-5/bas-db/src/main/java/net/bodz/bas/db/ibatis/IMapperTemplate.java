package net.bodz.bas.db.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Flush;

public interface IMapperTemplate<T, C>
        extends IMapper {

    List<T> all();

    List<T> filter(C criteria);

    T select(Object id);

    T selectByCodeName(String codeName);

    T selectPrev(Object id);

    T selectNext(Object id);

    void _insert(T obj);

    long insert(T obj);

    void update(T obj);

    boolean delete(Object id);

    Map<String, Number> count(C criteria);

    @Commit
    @Flush
    void flush();

}
