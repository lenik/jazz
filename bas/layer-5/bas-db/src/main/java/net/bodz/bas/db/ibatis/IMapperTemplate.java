package net.bodz.bas.db.ibatis;

import java.util.List;
import java.util.Map;

public interface IMapperTemplate<T, C>
        extends IMapper {

    List<T> all();

    List<T> filter(C criteria);

    T select(long id);

    T selectPrev(long id);

    T selectNext(long id);

    long insert(T obj);

    void update(T obj);

    boolean delete(int id);

    Map<String, Long> count();

}