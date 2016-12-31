package net.bodz.bas.db.ctx;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.db.ibatis.IbatisMapperProvider;
import net.bodz.bas.db.jdbc.BoneCPDataSourceProvider;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.db.jdbc.IDataSourceProvider;
import net.bodz.bas.db.jdbc.util.ISqlExecutor;
import net.bodz.bas.db.jdbc.util.SharedSqlExecutor;
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IAttributed;

public class DataContext
        extends AbstractQueryable
        implements Closeable, IAttributed, SqlSessionFactory {

    public static final String ATTRIBUTE_KEY = DataContext.class.getName();

    private ConnectOptions options;
    private IDataSourceProvider dataSourceProvider;
    private DataSource dataSource;
    private IbatisMapperProvider mapperProvider;
    private Map<String, Object> attributes;

    public DataContext(ConnectOptions opts) {
        if (opts == null)
            throw new NullPointerException("opts");
        this.options = opts;

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

    public ConnectOptions getOptions() {
        return options;
    }

    public IDataSourceProvider getDataSourceProvider() {
        return dataSourceProvider;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection()
            throws SQLException {
        return dataSource.getConnection();
    }

    public ISqlExecutor getSqlExecutor()
            throws SQLException {
        Connection connection = dataSource.getConnection();
        return new SharedSqlExecutor(connection);
    }

    public IMapperProvider getMapperProvider() {
        return mapperProvider;
    }

    public <mapper_t extends IMapper> mapper_t getMapper(Class<mapper_t> mapperClass) {
        return getMapper(mapperClass, false);
    }

    public <mapper_t extends IMapper> mapper_t getMapper(Class<mapper_t> mapperClass, boolean batch) {
        return getMapperProvider().getMapper(mapperClass, batch);
    }

    public <mapper_t extends IMapper> mapper_t getMapper(Class<mapper_t> mapperClass, SqlSession session) {
        return getMapperProvider().getMapper(mapperClass, session);
    }

    @Override
    public void close()
            throws IOException {
        if (dataSource instanceof Closeable)
            // shutdown connection pool if possible.
            ((Closeable) dataSource).close();
    }

    /** ⇱ Implementation Of {@link IAttributed}. */
    /* _____________________________ */static section.iface __ATTRS__;

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

    /** ⇱ Implementation Of {@link SqlSessionFactory}. */
/* _____________________________ */static section.iface __SESSION_FACTORY__;

    public SqlSessionFactory getSessionFactory() {
        return mapperProvider.getSqlSessionFactory();
    }

    @Override
    public Configuration getConfiguration() {
        return getSessionFactory().getConfiguration();
    }

    @Override
    public SqlSession openSession() {
        return getSessionFactory().openSession();
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return getSessionFactory().openSession(autoCommit);
    }

    @Override
    public SqlSession openSession(Connection connection) {
        return getSessionFactory().openSession(connection);
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        return getSessionFactory().openSession(level);
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
        return getSessionFactory().openSession(execType);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return getSessionFactory().openSession(execType, autoCommit);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return getSessionFactory().openSession(execType, level);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, Connection connection) {
        return getSessionFactory().openSession(execType, connection);
    }

}
