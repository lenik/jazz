package net.bodz.bas.db.batis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
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

import net.bodz.bas.c.org.postgresql.util.PgInetAddressTypeHandler;
import net.bodz.bas.c.org.postgresql.util.StateTypeHandler;
import net.bodz.bas.c.type.ClassNameComparator;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class MybatisMapperProvider
        implements IMapperProvider {

    static final Logger logger = LoggerFactory.getLogger(MybatisMapperProvider.class);

    private Set<Class<?>> mapperClasses = new TreeSet<>(ClassNameComparator.getInstance());
    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;

    public MybatisMapperProvider(DataSource dataSource) {
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

        TypeAliasRegistry registry = config.getTypeAliasRegistry();
        registry.registerAlias(MillisecondTypeHandler.class);
        registry.registerAlias(PgInetAddressTypeHandler.class);
        registry.registerAlias(StateTypeHandler.class);

        String[] builtins = { "co", "message" };
        for (String builtin : builtins) {
            String resName = "com/tinylily/model/share/" + builtin + ".xml";
            getClass().getClassLoader().getResource(resName);
            URL url = getClass().getClassLoader().getResource(resName);
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
            System.out.println(mapperClass);
            config.addMapper(mapperClass);
        }

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

}
