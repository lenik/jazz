package net.bodz.bas.db.ibatis;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
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
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.text.trie.BinaryReplacer;

@IndexedTypeLoader
public class IbatisMapperProvider
        implements
            IMapperProvider {

    static final Logger logger = LoggerFactory.getLogger(IbatisMapperProvider.class);

    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;

    public IbatisMapperProvider(DataSource dataSource) {
        if (dataSource == null)
            throw new NullPointerException("dataSource");
        this.dataSource = dataSource;
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

        List<IIbatisConfigurer> configurers = new ArrayList<IIbatisConfigurer>();
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
            for (Class<?> typeHandlerClass : typeIndex.list(TypeHandler.class, false)) {
                logger.debug("Register type handler: " + typeHandlerClass);
                typeHandlerRegistry.register(typeHandlerClass);
            }
            for (Class<?> aliasedClass : typeIndex.listIndexed(AliasedType.class)) {
                logger.debug("Register type alias for " + aliasedClass);
                typeAliasRegistry.registerAlias(aliasedClass);
            }
        } catch (Exception e) {
            throw new IllegalUsageError(e.getMessage(), e);
        }

        for (IIbatisConfigurer configurer : configurers)
            if (configurer.getPriority() > IIbatisConfigurer.PRIORITY_HIGH
                    && configurer.getPriority() <= IIbatisConfigurer.PRIORITY_MEDIUM)
                configurer.configure(config);

        for (IMapperXmlProvider prov : ServiceLoader.load(IMapperXmlProvider.class)) {
            for (String name : prov.getNames()) {
                MapperXml xml = prov.getXml(name);
                try {
                    loadMapperXml(config, xml);
                } catch (Exception e) { // BuilderException e
                    logger.errorf(e, "Error load %s: %s", //
                            xml.resourceName, e.getMessage());
                }
            }
        }

        Set<Class<?>> woMappers = MapperClassXmls.getLastInstance().getClassesWithoutXmls();
        logger.warnf("Add %d mappers without xml.", woMappers.size());
        for (Class<?> mapper : woMappers) {
            logger.warn("    Mapper-Class: ", mapper.getName());
            config.addMapper(mapper);
        }

        for (IIbatisConfigurer configurer : configurers)
            if (configurer.getPriority() >= IIbatisConfigurer.PRIORITY_LOW)
                configurer.configure(config);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(config);
    }

    protected boolean loadMapperXml(Configuration config, MapperXml xml) {
        String fqcn = xml.mapperClass.getName();
        if (config.isResourceLoaded("namespace:" + fqcn))
            return false; // already loaded.

        byte[] patched = BinaryReplacer.getIndexed().transform(xml.content);
        if (!Arrays.equals(xml.content, patched))
            logger.info("Patched: " + fqcn);

        XMLMapperBuilder xmlParser = new XMLMapperBuilder(//
                new ByteArrayInputStream(patched), //
                config, xml.resourceName, config.getSqlFragments(), fqcn);
        xmlParser.parse();
        return true;
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

    public boolean hasMapper(Class<?> mapperClass) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        return sqlSessionFactory.getConfiguration().hasMapper(mapperClass);
    }

    @Override
    public <M> M getMapper(Class<M> mapperClass, boolean batch) {
        if (!hasMapper(mapperClass))
            return null;
        SqlSession session = null;
        if (batch)
            session = getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        return getMapper(mapperClass, session);
    }

    public <T> T getMapper(Class<T> mapperClass, SqlSession session) {
        try {
            if (!hasMapper(mapperClass))
                return null;
        } catch (Exception e) {
            throw new LoadException(//
                    String.format("Failed to load mapper for %s: %s", mapperClass, e.getMessage()), e);
        }
        return instantiateMapper(mapperClass, session);
    }

    <T> T instantiateMapper(Class<T> mapperClass, SqlSession session) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        if (!sqlSessionFactory.getConfiguration().hasMapper(mapperClass))
            return null;

        AbstractMapperProxy handler;
        if (session != null) {
            handler = new SharedSessionMapperProxy(session, mapperClass);
        } else {
            handler = new PrivateSessionMapperProxy(sqlSessionFactory, mapperClass);
        }

        Object proxy = Proxy.newProxyInstance(mapperClass.getClassLoader(), //
                new Class<?>[] { mapperClass }, handler);
        handler.proxyThis = proxy;

        return mapperClass.cast(proxy);
    }

}
