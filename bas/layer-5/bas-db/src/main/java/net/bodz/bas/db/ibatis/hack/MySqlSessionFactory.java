package net.bodz.bas.db.ibatis.hack;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MySqlSessionFactory
        extends DecoratedSqlSessionFactory {

    private static final long serialVersionUID = 1L;

    public MySqlSessionFactory(SqlSessionFactory _orig) {
        super(_orig);
    }

    @Override
    protected SqlSession wrapSession(SqlSession session) {
        return new MySqlSession(session);
    }

}
