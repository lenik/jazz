package net.bodz.bas.proxy.java.sql;

import java.sql.SQLException;
import java.sql.Wrapper;

import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.bas.t.model.IWrapper;

public abstract class AbstractJavasqlWrapper<T>
        extends AbstractDecorator<T>
        implements IWrapper<T>, Wrapper {

    private static final long serialVersionUID = 1L;

    public AbstractJavasqlWrapper(T _orig) {
        super(_orig);
    }

    @Override
    public final <_T> _T unwrap(Class<_T> iface)
            throws SQLException {
        Class<? extends Object> realType = _orig.getClass();
        if (iface.isAssignableFrom(realType))
            return iface.cast(_orig);
        else
            return _unwrap(iface);
    }

    protected abstract <_T> _T _unwrap(Class<_T> iface)
            throws SQLException;

    @Override
    public final boolean isWrapperFor(Class<?> iface)
            throws SQLException {
        Class<? extends Object> realType = _orig.getClass();
        if (iface.isAssignableFrom(realType))
            return true;
        else
            return _isWrapperFor(iface);
    }

    protected abstract boolean _isWrapperFor(Class<?> iface)
            throws SQLException;

}
