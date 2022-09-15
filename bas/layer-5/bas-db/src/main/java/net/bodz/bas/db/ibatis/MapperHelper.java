package net.bodz.bas.db.ibatis;

import java.util.List;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ibatis.sql.SelectOptions;

public class MapperHelper<T, M>
        extends Dummy<T, M> {

    Class<T> entityType;
    Class<M> maskType;

    public MapperHelper() {
        maskType = TypeParam.infer1(getClass(), MapperHelper.class, 1);
    }

    public MapperHelper(Class<T> entityType, Class<M> maskType) {
        this.maskType = maskType;
    }

    @Override
    public M mask() {
        try {
            return maskType.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}

class Dummy<T, M>
        implements
            IGenericMapper<T, M> {

    @Override
    public List<T> all(SelectOptions opt) {
        return null;
    }

    @Override
    public List<T> filter(M mask, SelectOptions opt) {
        return null;
    }

    @Override
    public long insert(T obj) {
        return 0;
    }

    @Override
    public int deleteFor(M mask) {
        return 0;
    }

    @Override
    public long count(M mask) {
        return 0;
    }

    @Override
    public void flush() {
    }

}