package net.bodz.bas.db.ibatis.hack;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedSqlSession
        extends AbstractDecorator<SqlSession>
        implements
            SqlSession {

    private static final long serialVersionUID = 1L;

    public DecoratedSqlSession(SqlSession _orig) {
        super(_orig);
    }

    /**
     * @param <T>
     * @param statement
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectOne(java.lang.String)
     */
    @Override
    public <T> T selectOne(String statement) {
        return getWrapped().selectOne(statement);
    }

    /**
     * @param <T>
     * @param statement
     * @param parameter
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectOne(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return getWrapped().selectOne(statement, parameter);
    }

    /**
     * @param <E>
     * @param statement
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectList(java.lang.String)
     */
    @Override
    public <E> List<E> selectList(String statement) {
        return getWrapped().selectList(statement);
    }

    /**
     * @param <E>
     * @param statement
     * @param parameter
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectList(java.lang.String, java.lang.Object)
     */
    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return getWrapped().selectList(statement, parameter);
    }

    /**
     * @param <E>
     * @param statement
     * @param parameter
     * @param rowBounds
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectList(java.lang.String, java.lang.Object,
     *      org.apache.ibatis.session.RowBounds)
     */
    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return getWrapped().selectList(statement, parameter, rowBounds);
    }

    /**
     * @param <K>
     * @param <V>
     * @param statement
     * @param mapKey
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectMap(java.lang.String, java.lang.String)
     */
    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return getWrapped().selectMap(statement, mapKey);
    }

    /**
     * @param <K>
     * @param <V>
     * @param statement
     * @param parameter
     * @param mapKey
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectMap(java.lang.String, java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return getWrapped().selectMap(statement, parameter, mapKey);
    }

    /**
     * @param <K>
     * @param <V>
     * @param statement
     * @param parameter
     * @param mapKey
     * @param rowBounds
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectMap(java.lang.String, java.lang.Object,
     *      java.lang.String, org.apache.ibatis.session.RowBounds)
     */
    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return getWrapped().selectMap(statement, parameter, mapKey, rowBounds);
    }

    /**
     * @param <T>
     * @param statement
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectCursor(java.lang.String)
     */
    @Override
    public <T> Cursor<T> selectCursor(String statement) {
        return getWrapped().selectCursor(statement);
    }

    /**
     * @param <T>
     * @param statement
     * @param parameter
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectCursor(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter) {
        return getWrapped().selectCursor(statement, parameter);
    }

    /**
     * @param <T>
     * @param statement
     * @param parameter
     * @param rowBounds
     * @return
     * @see org.apache.ibatis.session.SqlSession#selectCursor(java.lang.String, java.lang.Object,
     *      org.apache.ibatis.session.RowBounds)
     */
    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
        return getWrapped().selectCursor(statement, parameter, rowBounds);
    }

    /**
     * @param statement
     * @param parameter
     * @param handler
     * @see org.apache.ibatis.session.SqlSession#select(java.lang.String, java.lang.Object,
     *      org.apache.ibatis.session.ResultHandler)
     */
    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        getWrapped().select(statement, parameter, handler);
    }

    /**
     * @param statement
     * @param handler
     * @see org.apache.ibatis.session.SqlSession#select(java.lang.String,
     *      org.apache.ibatis.session.ResultHandler)
     */
    @Override
    public void select(String statement, ResultHandler handler) {
        getWrapped().select(statement, handler);
    }

    /**
     * @param statement
     * @param parameter
     * @param rowBounds
     * @param handler
     * @see org.apache.ibatis.session.SqlSession#select(java.lang.String, java.lang.Object,
     *      org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler)
     */
    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        getWrapped().select(statement, parameter, rowBounds, handler);
    }

    /**
     * @param statement
     * @return
     * @see org.apache.ibatis.session.SqlSession#insert(java.lang.String)
     */
    @Override
    public int insert(String statement) {
        return getWrapped().insert(statement);
    }

    /**
     * @param statement
     * @param parameter
     * @return
     * @see org.apache.ibatis.session.SqlSession#insert(java.lang.String, java.lang.Object)
     */
    @Override
    public int insert(String statement, Object parameter) {
        return getWrapped().insert(statement, parameter);
    }

    /**
     * @param statement
     * @return
     * @see org.apache.ibatis.session.SqlSession#update(java.lang.String)
     */
    @Override
    public int update(String statement) {
        return getWrapped().update(statement);
    }

    /**
     * @param statement
     * @param parameter
     * @return
     * @see org.apache.ibatis.session.SqlSession#update(java.lang.String, java.lang.Object)
     */
    @Override
    public int update(String statement, Object parameter) {
        return getWrapped().update(statement, parameter);
    }

    /**
     * @param statement
     * @return
     * @see org.apache.ibatis.session.SqlSession#delete(java.lang.String)
     */
    @Override
    public int delete(String statement) {
        return getWrapped().delete(statement);
    }

    /**
     * @param statement
     * @param parameter
     * @return
     * @see org.apache.ibatis.session.SqlSession#delete(java.lang.String, java.lang.Object)
     */
    @Override
    public int delete(String statement, Object parameter) {
        return getWrapped().delete(statement, parameter);
    }

    /**
     *
     * @see org.apache.ibatis.session.SqlSession#commit()
     */
    @Override
    public void commit() {
        getWrapped().commit();
    }

    /**
     * @param force
     * @see org.apache.ibatis.session.SqlSession#commit(boolean)
     */
    @Override
    public void commit(boolean force) {
        getWrapped().commit(force);
    }

    /**
     *
     * @see org.apache.ibatis.session.SqlSession#rollback()
     */
    @Override
    public void rollback() {
        getWrapped().rollback();
    }

    /**
     * @param force
     * @see org.apache.ibatis.session.SqlSession#rollback(boolean)
     */
    @Override
    public void rollback(boolean force) {
        getWrapped().rollback(force);
    }

    /**
     * @return
     * @see org.apache.ibatis.session.SqlSession#flushStatements()
     */
    @Override
    public List<BatchResult> flushStatements() {
        return getWrapped().flushStatements();
    }

    /**
     *
     * @see org.apache.ibatis.session.SqlSession#close()
     */
    @Override
    public void close() {
        getWrapped().close();
    }

    /**
     *
     * @see org.apache.ibatis.session.SqlSession#clearCache()
     */
    @Override
    public void clearCache() {
        getWrapped().clearCache();
    }

    /**
     * @return
     * @see org.apache.ibatis.session.SqlSession#getConfiguration()
     */
    @Override
    public Configuration getConfiguration() {
        return getWrapped().getConfiguration();
    }

    /**
     * @param <T>
     * @param type
     * @return
     * @see org.apache.ibatis.session.SqlSession#getMapper(java.lang.Class)
     */
    @Override
    public <T> T getMapper(Class<T> type) {
        return getWrapped().getMapper(type);
    }

    /**
     * @return
     * @see org.apache.ibatis.session.SqlSession#getConnection()
     */
    @Override
    public Connection getConnection() {
        return getWrapped().getConnection();
    }

}
