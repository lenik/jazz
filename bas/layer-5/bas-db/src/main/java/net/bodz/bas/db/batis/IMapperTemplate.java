package net.bodz.bas.db.batis;

import java.util.List;
import java.util.Map;

public interface IMapperTemplate<T, C>
        extends IMapper {

    List<T> all();

    List<T> filter(C criteria);

    T select(long id);

    int insert(T obj);

    void update(T obj);

    boolean delete(int id);

    Map<String, Long> count();

}
