package net.bodz.bas.db.ibatis.hack;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactoryBuilder
        extends SqlSessionFactoryBuilder {

    @Override
    public SqlSessionFactory build(Configuration config) {
        SqlSessionFactory orig = super.build(config);
        return new MySqlSessionFactory(orig);
    }

}
