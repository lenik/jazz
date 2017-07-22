package net.bodz.bas.db.ibatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.type.ClassNameComparator;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedTypeLoader
public class IbatisMapperProvider
        extends AbstractMapperProvider {

    static final Logger logger = LoggerFactory.getLogger(IbatisMapperProvider.class);

    private Set<Class<?>> mapperClasses = new TreeSet<>(ClassNameComparator.getInstance());
    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;

    public IbatisMapperProvider(DataSource dataSource) {
        if (dataSource == null)
            throw new NullPointerException("dataSource");
        this.dataSource = dataSource;
        addIndexedClasses();
    }

    public void addIndexedClasses() {
        for (Class<?> mapperClass : IndexedTypes.list(IMapper.class, true)) {
            mapperClasses.add(mapperClass);
        }
    }

    /**
     * @see SqlSessionFactoryBuilder#build(java.io.Reader)
     */
    public synchronized SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null)
            sqlSessionFactory = buildSqlSessionFactory();
        return sqlSessionFactory;
    }

    SqlSessionFactory buildSqlSessionFactory() {
        Configuration config = new Configuration();

        List<IIbatisConfigurer> configurers = new ArrayList<>();
        for (IIbatisConfigurer configurer : ServiceLoader.load(IIbatisConfigurer.class))
            configurers.add(configurer);

        config.setEnvironment(buildEnvironment());

        for (IIbatisConfigurer configurer : configurers)
            if (configurer.getPriority() <= IIbatisConfigurer.PRIORITY_HIGH)
                configurer.configure(config);

        TypeIndex typeIndex = TypeIndex.getInstance(ClassLoaders.getRuntimeClassLoader());

        TypeAliasRegistry typeAliasRegistry = config.getTypeAliasRegistry();
        TypeHandlerRegistry typeHandlerRegistry = config.getTypeHandlerRegistry();
        try {
            for (Class<?> typeHandlerClass : typeIndex.list(TypeHandler.class, false))
                typeHandlerRegistry.register(typeHandlerClass);
            for (Class<?> aliasedClass : typeIndex.listIndexed(AliasedType.class))
                typeAliasRegistry.registerAlias(aliasedClass);
        } catch (Exception e) {
            throw new IllegalUsageError(e.getMessage(), e);
        }

        for (IIbatisConfigurer configurer : configurers)
            if (configurer.getPriority() > IIbatisConfigurer.PRIORITY_HIGH
                    && configurer.getPriority() <= IIbatisConfigurer.PRIORITY_MEDIUM)
                configurer.configure(config);

        for (Class<?> mapperClass : mapperClasses) {
            logger.log("Add Mapper: ", mapperClass);
            try {
                config.addMapper(mapperClass);
            } catch (BuilderException e) {
                logger.error("Failed to add mapper: " + mapperClass, e);
                throw e;
            }
        }

        for (IIbatisConfigurer configurer : configurers)
            if (configurer.getPriority() >= IIbatisConfigurer.PRIORITY_LOW)
                configurer.configure(config);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(config);
    }

    Environment buildEnvironment() {
        String envId = "development";
        Environment.Builder envBuilder = new Environment.Builder(envId);
        TransactionFactory txFactory = new JdbcTransactionFactory();
        envBuilder.transactionFactory(txFactory);
        envBuilder.dataSource(dataSource);
        Environment env = envBuilder.build();
        return env;
    }

    /** ⇱ Implementation Of {@link IMapperProvider}. */
    /* _____________________________ */static section.iface __PROVIDER__;

    public <M extends IMapper> boolean hasMapper(Class<M> mapperClass) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        return sqlSessionFactory.getConfiguration().hasMapper(mapperClass);
    }

    @Override
    public <M extends IMapper> M getMapper(Class<M> mapperClass, boolean batch) {
        if (!hasMapper(mapperClass))
            return null;
        SqlSession session = null;
        if (batch)
            session = getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        return getMapper(mapperClass, session);
    }

    @Override
    public <T extends IMapper> T getMapper(Class<T> mapperClass, SqlSession session) {
        try {
            if (!hasMapper(mapperClass))
                return null;
        } catch (Exception e) {
            throw new LoadException(//
                    String.format("Failed to load mapper for %s: %s", mapperClass, e.getMessage()), e);
        }
        return instantiateMapper(mapperClass, session);
    }

    <T extends IMapper> T instantiateMapper(Class<T> mapperClass, SqlSession session) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        if (!sqlSessionFactory.getConfiguration().hasMapper(mapperClass))
            return null;

        InvocationHandler handler;
        if (session != null) {
            handler = new SharedSessionMapperProxy(session, mapperClass);
        } else {
            handler = new PrivateSessionMapperProxy(sqlSessionFactory, mapperClass);
        }

        Object proxy = Proxy.newProxyInstance(mapperClass.getClassLoader(), //
                new Class<?>[] { mapperClass }, handler);
        return mapperClass.cast(proxy);
    }

}
