package net.bodz.lily.test;

import java.lang.reflect.Type;
import java.util.Random;

import org.junit.Assert;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.db.test.DaoTestConfig;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.util.mapper.TableProfiles;

public abstract class AbstractTableViewTest<T extends CoObject, M, mapper_t extends IMapperTemplate<T, M>>
        extends Assert {

    protected DataContext context = findPreferredDataContext();
    protected TableProfiles tables = new TableProfiles(context);

    protected final Class<T> entityClass;
    protected final Class<M> maskClass;
    protected final Class<mapper_t> mapperClass;

    protected final Random random = new Random();

    public AbstractTableViewTest() {
        Type[] typeArgs = TypeParam.getTypeArgs(getClass(), AbstractTableViewTest.class);
        entityClass = TypeParam.bound1(typeArgs[0]);
        maskClass = TypeParam.bound1(typeArgs[1]);
        mapperClass = TypeParam.bound1(typeArgs[2]);
    }

    protected DataContext getContext() {
        return context;
    }

    protected DataContext findPreferredDataContext() {
        DataHub dataContexts = DataHub.getPreferredHub();
        if (dataContexts == null)
            throw new IllegalConfigException("No data contexts installed.");
        return dataContexts.getTest();
    }

    public DaoTestConfig getConfig() {
        return DaoTestConfig.global;
    }

    public mapper_t getMapper() {
        mapper_t mapper = context.getMapper(mapperClass);
        if (mapper == null)
            throw new NullPointerException("No mapper for " + mapperClass);
        return mapper;
    }

}
