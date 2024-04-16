package net.bodz.bas.db.ibatis.hack;

import java.sql.Connection;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedSqlSessionFactory
        extends AbstractDecorator<SqlSessionFactory>
        implements
            SqlSessionFactory {

    private static final long serialVersionUID = 1L;

    public DecoratedSqlSessionFactory(SqlSessionFactory _orig) {
        super(_orig);
    }

    /**
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession()
     */
    @Override
    public SqlSession openSession() {
        return wrapSession(getWrapped().openSession());
    }

    /**
     * @param autoCommit
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(boolean)
     */
    @Override
    public SqlSession openSession(boolean autoCommit) {
        return wrapSession(getWrapped().openSession(autoCommit));
    }

    /**
     * @param connection
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(java.sql.Connection)
     */
    @Override
    public SqlSession openSession(Connection connection) {
        return wrapSession(getWrapped().openSession(connection));
    }

    /**
     * @param level
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(org.apache.ibatis.session.TransactionIsolationLevel)
     */
    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        return wrapSession(getWrapped().openSession(level));
    }

    /**
     * @param execType
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(org.apache.ibatis.session.ExecutorType)
     */
    @Override
    public SqlSession openSession(ExecutorType execType) {
        return wrapSession(getWrapped().openSession(execType));
    }

    /**
     * @param execType
     * @param autoCommit
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(org.apache.ibatis.session.ExecutorType,
     *      boolean)
     */
    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return wrapSession(getWrapped().openSession(execType, autoCommit));
    }

    /**
     * @param execType
     * @param level
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(org.apache.ibatis.session.ExecutorType,
     *      org.apache.ibatis.session.TransactionIsolationLevel)
     */
    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return wrapSession(getWrapped().openSession(execType, level));
    }

    /**
     * @param execType
     * @param connection
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#openSession(org.apache.ibatis.session.ExecutorType,
     *      java.sql.Connection)
     */
    @Override
    public SqlSession openSession(ExecutorType execType, Connection connection) {
        return wrapSession(getWrapped().openSession(execType, connection));
    }

    /**
     * @return
     * @see org.apache.ibatis.session.SqlSessionFactory#getConfiguration()
     */
    @Override
    public Configuration getConfiguration() {
        return getWrapped().getConfiguration();
    }

    protected SqlSession wrapSession(SqlSession session) {
        return session;
    }

}
