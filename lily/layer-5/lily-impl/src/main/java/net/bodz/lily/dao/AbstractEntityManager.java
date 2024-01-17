package net.bodz.lily.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.lily.criterion.ICriterion;

public class AbstractEntityManager<T, //
        mapper_t extends IEntityMapper<T>>
        implements
            IEntityManager<T> {

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
    public List<T> filter(ICriterion criteria, SelectOptions opt) {
        return mapper.filter(criteria, opt);
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
    public long deleteFor(ICriterion criteria) {
        return mapper.deleteFor(criteria);
    }

    @Override
    public long count(ICriterion criteria) {
        return mapper.count(criteria);
    }

//    @Override
//    public mask_t mask() {
//        return mapper.mask();
//    }

    @Override
    public List<T> filter(ICriterion criteria) {
        return mapper.filter(criteria);
    }

    @Override
    public <K> Map<K, T> filterMap(Function<T, K> keyf, ICriterion criteria) {
        return mapper.filterMap(keyf, criteria);
    }

    @Override
    public <K> Map<K, T> filterMap(Function<T, K> keyf, ICriterion criteria, SelectOptions opt) {
        return mapper.filterMap(keyf, criteria, opt);
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
