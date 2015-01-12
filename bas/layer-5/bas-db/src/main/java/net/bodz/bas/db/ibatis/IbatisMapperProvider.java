package net.bodz.bas.db.ibatis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

import net.bodz.bas.c.type.ClassNameComparator;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class IbatisMapperProvider
        implements IMapperProvider {

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
        config.setEnvironment(buildEnvironment());

        TypeAliasRegistry typeAliasRegistry = config.getTypeAliasRegistry();
        TypeHandlerRegistry typeHandlerRegistry = config.getTypeHandlerRegistry();
        try {
            for (Class<?> typeHandlerClass : TypeIndex.forClass(TypeHandlerImpl.class, false))
                typeHandlerRegistry.register(typeHandlerClass);
            for (Class<?> aliasedClass : TypeIndex.forClass(Aliased.class))
                typeAliasRegistry.registerAlias(aliasedClass);
        } catch (Exception e) {
            throw new IllegalUsageError(e.getMessage(), e);
        }

        List<String> builtins = new ArrayList<>();
        for (String s : Arrays.asList("co", "mi", "message"))
            builtins.add("com/tinylily/model/share/" + s + ".xml");

        for (String resName : builtins) {
            getClass().getClassLoader().getResource(resName);
            URL url = getClass().getClassLoader().getResource(resName);
            if (url == null) {
                System.err.println("Bad resource: " + resName);
                continue;
            }
            try {
                InputStream in = url.openStream();
                XMLMapperBuilder xmb = new XMLMapperBuilder(in, config, resName, config.getSqlFragments());
                xmb.parse();
                in.close();
            } catch (IOException e) {
                throw new Error(e);
            }
        }

        for (Class<?> mapperClass : mapperClasses) {
            logger.log("Add Mapper: ", mapperClass);
            config.addMapper(mapperClass);
        }

        for (IIbatisConfigurer configurer : ServiceLoader.load(IIbatisConfigurer.class))
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

    @Override
    public <T extends IMapper> T getMapper(Class<T> mapperClass) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        if (sqlSessionFactory.getConfiguration().hasMapper(mapperClass)) {
            TxMapperProxy handler = new TxMapperProxy(mapperClass);
            Object proxy = Proxy.newProxyInstance(mapperClass.getClassLoader(), //
                    new Class<?>[] { mapperClass }, handler);
            return mapperClass.cast(proxy);
        }
        return null;
    }

    class TxMapperProxy
            implements InvocationHandler {

        Class<?> mapperClass;

        public TxMapperProxy(Class<?> mapperClass) {
            this.mapperClass = mapperClass;
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] args)
                throws Throwable {
            Object result;
            SqlSession session = getSqlSessionFactory().openSession();
            try {
                Object proxy = session.getMapper(mapperClass);
                result = method.invoke(proxy, args);
                session.commit();
            } finally {
                session.close();
            }
            return result;
        }

    }

    @Override
    public <M extends IMapper> M getMapperForObject(Class<?> objClass) {
        Class<M> mapperClass = (Class<M>) IMapper.fn.getMapperClass(objClass);
        return getMapper(mapperClass);
    }

}
