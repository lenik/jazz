package net.bodz.bas.db.ctx;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.db.ibatis.IbatisMapperProvider;
import net.bodz.bas.db.jdbc.BoneCPDataSourceProvider;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.db.jdbc.IDataSourceProvider;
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IAttributed;

public class DataContext
        extends AbstractQueryable
        implements Closeable, IAttributed {

    public static final String ATTRIBUTE_KEY = DataContext.class.getName();

    private IDataSourceProvider dataSourceProvider;
    private DataSource dataSource;
    private IMapperProvider mapperProvider;
    private Map<String, Object> attributes;

    public DataContext(ConnectOptions opts) {
        if (opts == null)
            throw new NullPointerException("opts");

        dataSourceProvider = new BoneCPDataSourceProvider(opts);

        dataSource = dataSourceProvider.getDataSource();
        if (dataSource == null)
            throw new NullPointerException("dataSource");

        mapperProvider = new IbatisMapperProvider(dataSource);
        attributes = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationClass) {
        if (specificationClass == null)
            throw new NullPointerException("specificationClass");
        if (specificationClass == IMapperProvider.class)
            return (spec_t) getMapperProvider();
        if (specificationClass == DataSource.class)
            return (spec_t) getDataSource();
        if (IMapper.class.isAssignableFrom(specificationClass)) {
            Class<? extends IMapper> mapperClass = (Class<? extends IMapper>) specificationClass;
            spec_t mapper = (spec_t) getMapper(mapperClass);
            return mapper;
        }
        return super.query(specificationClass);
    }

    public IDataSourceProvider getDataSourceProvider() {
        return dataSourceProvider;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public IMapperProvider getMapperProvider() {
        return mapperProvider;
    }

    public <mapper_t extends IMapper> mapper_t getMapper(Class<mapper_t> mapperClass) {
        return getMapper(mapperClass, true);
    }

    public <mapper_t extends IMapper> mapper_t getMapper(Class<mapper_t> mapperClass, boolean autoCommit) {
        mapper_t mapper = getMapperProvider().getMapper(mapperClass, autoCommit);
        return mapper;
    }

    public <T, C> IMapperTemplate<T, C> getMapperFor(Class<T> entityClass) {
        return getMapperFor(entityClass, true);
    }

    @SuppressWarnings("unchecked")
    public <T, C> IMapperTemplate<T, C> getMapperFor(Class<T> entityClass, boolean autoCommit) {
        Class<? extends IMapper> mapperClass = IMapper.fn.getMapperClass(entityClass);
        if (mapperClass == null)
            throw new IllegalArgumentException("unmapped entity: " + entityClass);
        IMapper mapper = getMapper(mapperClass);
        return (IMapperTemplate<T, C>) mapper;
    }

    @Override
    public void close()
            throws IOException {
        if (dataSource instanceof Closeable)
            // shutdown connection pool if possible.
            ((Closeable) dataSource).close();
    }

    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        T value = (T) attributes.get(name);
        if (value == null)
            value = defaultValue;
        return value;
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

}
