package net.bodz.bas.db.ibatis;

import java.util.List;

import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.lily.criterion.ICriterion;

@ExcludedFromIndex
public class MapperHelper<T>
        extends Dummy<T> {

    Class<T> entityType;

    public MapperHelper() {
    }

    public MapperHelper(Class<T> entityType) {
        this.entityType = entityType;
    }

}

class Dummy<T>
        implements
            IGenericMapper<T> {

    @Override
    public List<T> all(SelectOptions opt) {
        return null;
    }

    @Override
    public List<T> filter(ICriterion mask, SelectOptions opt) {
        return null;
    }

    @Override
    public long insert(T obj) {
        return 0;
    }

    @Override
    public int deleteObject(Object obj) {
        return 0;
    }

    @Override
    public long deleteFor(ICriterion mask) {
        return 0;
    }

    @Override
    public long count(ICriterion criteria, SelectOptions options) {
        return 0;
    }

    @Override
    public void flush() {
    }

}