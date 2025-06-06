package net.bodz.bas.db.ctx;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.db.ibatis.IbatisMapperProvider;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.db.jdbc.HikariDataSourceProvider;
import net.bodz.bas.db.jdbc.IDataSourceProvider;
import net.bodz.bas.db.jdbc.util.ISqlExecutor;
import net.bodz.bas.db.jdbc.util.SharedSqlExecutor;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.rtx.IMutableAttributes;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.MutableAttributes;

public class DataContext
        implements IQueryable,
                   Closeable,
                   IMutableAttributes,
                   IJsonForm,
                   SqlSessionFactory {

//    public static final String ATTRIBUTE_KEY = DataContext.class.getName();

    static int nextId = 1;
    int id;

    private final ConnectOptions options;
    private final IDataSourceProvider dataSourceProvider;
    private final DataSource dataSource;
    private final IbatisMapperProvider mapperProvider;
    private final MutableAttributes attributes;

    public DataContext(ConnectOptions opts) {
        if (opts == null)
            throw new NullPointerException("opts");
        this.options = opts;

        dataSourceProvider = new HikariDataSourceProvider(opts);

        dataSource = dataSourceProvider.getDataSource();
        if (dataSource == null)
            throw new NullPointerException("dataSource");

        mapperProvider = new IbatisMapperProvider(dataSource);
        attributes = new MutableAttributes();
    }

    public String getId() {
        return id + "@" + options.getId();
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
        return IQueryable.super.query(specificationClass);
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

    public final <mapper_t> mapper_t getMapper(Class<mapper_t> mapperClass) {
        return getMapper(mapperClass, false);
    }

    public <mapper_t> mapper_t getMapper(Class<mapper_t> mapperClass, boolean batch) {
        return getMapperProvider().getMapper(mapperClass, batch);
    }

    public <mapper_t> mapper_t getMapper(Class<mapper_t> mapperClass, SqlSession session) {
        IMapperProvider provider = getMapperProvider();
        IbatisMapperProvider batis = (IbatisMapperProvider) provider;
        return batis.getMapper(mapperClass, session);
    }

    public final <mapper_t> mapper_t requireMapper(Class<mapper_t> mapperClass) {
        return requireMapper(mapperClass, false);
    }

    public <mapper_t> mapper_t requireMapper(Class<mapper_t> mapperClass, boolean batch) {
        mapper_t mapper = getMapper(mapperClass, batch);
        if (mapper == null)
            throw new IllegalUsageException("No mapper for " + mapperClass);
        return mapper;
    }

    public <mapper_t> mapper_t requireMapper(Class<mapper_t> mapperClass, SqlSession session) {
        mapper_t mapper = getMapper(mapperClass, session);
        if (mapper == null)
            throw new IllegalUsageException("No mapper for " + mapperClass);
        return mapper;
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
    public Set<String> getAttributeNames() {
        return attributes.getAttributeNames();
    }

    @Override
    public boolean isAttributePresent(String name) {
        return attributes.isAttributePresent(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return attributes.getAttribute(name, defaultValue);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.removeAttribute(name);
    }

    /** ⇱ Implementation Of {@link IJsonForm}. */
    /* _____________________________ */static section.iface __JSON__;

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonObject _options = o.getJsonObject("options");
        if (_options != null)
            options.jsonIn(_options, opts);
        o.getMap("attributes", attributes.getAttributeMap(), SortOrder.KEEP, jv -> jv);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.key("options");
        options.jsonOutValue(out, opts);
        out.entry("attributes", attributes);
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

    /**
     * @TODO shortcuts...
     */
    public ResultSet query(String sql)
            throws SQLException {
        DataSource ds = getDataSource();
        Connection conn = ds.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

}
