package net.bodz.bas.db.batis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.io.res.builtin.URLResource;

public class MybatisMapperProvider
        implements IMapperProvider {

    private Set<Class<?>> mapperClasses = new HashSet<>();
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

        for (Class<?> mapperClass : mapperClasses)
            config.addMapper(mapperClass);

        String resName = "user/batis/Tag.xml";
        URL url = getClass().getClassLoader().getResource(resName);
        try {
            URLResource res = new URLResource(url);
            InputStream in = res.newInputStream();
            XMLMapperBuilder xmb = new XMLMapperBuilder(in, config, resName, null);
            xmb.parse();
        } catch (IOException e) {
            throw new Error(e);
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
