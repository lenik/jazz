package net.bodz.bas.db.batis;

import java.util.List;

public interface IMapperTemplate<T, C>
        extends IMapper {

    List<T> all();

    List<T> filter(C criteria);

    T select(int id);

    int insert(T obj);

    void update(T obj);

    boolean delete(int id);

}
