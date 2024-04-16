package net.bodz.bas.db.ibatis.hack;

import org.apache.ibatis.session.SqlSession;

public class MySqlSession
        extends DecoratedSqlSession {

    private static final long serialVersionUID = 1L;

    public MySqlSession(SqlSession _orig) {
        super(_orig);
    }

}
