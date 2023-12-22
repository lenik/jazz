package net.bodz.lily.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;

public class AbstractEntityManager<T, mask_t, //
        mapper_t extends IEntityMapper<T, mask_t>>
        implements
            IEntityManager<T, mask_t> {

    protected DataContext dataContext;
    protected mapper_t mapper;

    public AbstractEntityManager(DataContext dataContext, Class<mapper_t> mapperClass) {
        this.dataContext = dataContext;
        this.mapper = dataContext.getMapper(mapperClass);
    }

    @Override
    public List<T> all() {
        return mapper.all();
    }

    @Override
    public List<T> all(SelectOptions opt) {
        return mapper.all(opt);
    }

    @Override
    public List<T> filter(mask_t mask, SelectOptions opt) {
        return mapper.filter(mask, opt);
    }

    @Override
    public long insert(T obj) {
        return mapper.insert(obj);
    }

    @Override
    public int deleteObject(Object obj) {
        return mapper.deleteObject(obj);
    }

    @Override
    public long deleteFor(mask_t mask) {
        return mapper.deleteFor(mask);
    }

    @Override
    public long count(mask_t mask) {
        return mapper.count(mask);
    }

    @Override
    public mask_t mask() {
        return mapper.mask();
    }

    @Override
    public List<T> filter(mask_t mask) {
        return mapper.filter(mask);
    }

    @Override
    public <K> Map<K, T> filterMap(Function<T, K> keyf, mask_t mask) {
        return mapper.filterMap(keyf, mask);
    }

    @Override
    public <K> Map<K, T> filterMap(Function<T, K> keyf, mask_t mask, SelectOptions opt) {
        return mapper.filterMap(keyf, mask, opt);
    }

    @Override
    public T select(Object id) {
        return mapper.select(id);
    }

    @Override
    @Deprecated
    public long insertWithId(T obj) {
        return mapper.insertWithId(obj);
    }

    public T selectByCodeName(String codeName) {
        return mapper.selectByCodeName(codeName);
    }

    @Override
    public long insertExplicit(T obj) {
        return mapper.insertExplicit(obj);
    }

    @Override
    public long update(T obj) {
        return mapper.update(obj);
    }

    @Override
    public boolean delete(Object id) {
        return mapper.delete(id);
    }

}
