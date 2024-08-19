package net.bodz.bas.log;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedBaseLogger
        extends AbstractDecorator<IBaseLogger> {

    private static final long serialVersionUID = 1L;

    public DecoratedBaseLogger(IBaseLogger _orig) {
        super(_orig);
    }

    public boolean isStderrEnabled() {
        return getWrapped().isStderrEnabled();
    }

    public boolean isStderrEnabled(int delta) {
        return getWrapped().isStderrEnabled(delta);
    }

    public void _stderr(int delta, Throwable e, Object message) {
        getWrapped()._stderr(delta, e, message);
    }

    public boolean isStdoutEnabled() {
        return getWrapped().isStdoutEnabled();
    }

    public boolean isStdoutEnabled(int delta) {
        return getWrapped().isStdoutEnabled(delta);
    }

    public void _stdout(int delta, Throwable e, Object message) {
        getWrapped()._stdout(delta, e, message);
    }

    public boolean isFatalEnabled() {
        return getWrapped().isFatalEnabled();
    }

    public boolean isFatalEnabled(int delta) {
        return getWrapped().isFatalEnabled(delta);
    }

    public boolean _fatal(int delta, Throwable e, Object message) {
        return getWrapped()._fatal(delta, e, message);
    }

    public boolean isErrorEnabled() {
        return getWrapped().isErrorEnabled();
    }

    public boolean isErrorEnabled(int delta) {
        return getWrapped().isErrorEnabled(delta);
    }

    public boolean _error(int delta, Throwable e, Object message) {
        return getWrapped()._error(delta, e, message);
    }

    public boolean isWarnEnabled() {
        return getWrapped().isWarnEnabled();
    }

    public boolean isWarnEnabled(int delta) {
        return getWrapped().isWarnEnabled(delta);
    }

    public void _warn(int delta, Throwable e, Object message) {
        getWrapped()._warn(delta, e, message);
    }

    public boolean isMesgEnabled() {
        return getWrapped().isMesgEnabled();
    }

    public boolean isMesgEnabled(int delta) {
        return getWrapped().isMesgEnabled(delta);
    }

    public void _mesg(int delta, Throwable e, Object message) {
        getWrapped()._mesg(delta, e, message);
    }

    public boolean isInfoEnabled() {
        return getWrapped().isInfoEnabled();
    }

    public boolean isInfoEnabled(int delta) {
        return getWrapped().isInfoEnabled(delta);
    }

    public void _info(int delta, Throwable e, Object message) {
        getWrapped()._info(delta, e, message);
    }

    public boolean isLogEnabled() {
        return getWrapped().isLogEnabled();
    }

    public boolean isLogEnabled(int delta) {
        return getWrapped().isLogEnabled(delta);
    }

    public void _log(int delta, Throwable e, Object message) {
        getWrapped()._log(delta, e, message);
    }

    public boolean isDebugEnabled() {
        return getWrapped().isDebugEnabled();
    }

    public boolean isDebugEnabled(int delta) {
        return getWrapped().isDebugEnabled(delta);
    }

    public void _debug(int delta, Throwable e, Object message) {
        getWrapped()._debug(delta, e, message);
    }

    public boolean isTraceEnabled() {
        return getWrapped().isTraceEnabled();
    }

    public boolean isTraceEnabled(int delta) {
        return getWrapped().isTraceEnabled(delta);
    }

    public void _trace(int delta, Throwable e, Object message) {
        getWrapped()._trace(delta, e, message);
    }

    public boolean isStatusEnabled() {
        return getWrapped().isStatusEnabled();
    }

    public boolean isStatusEnabled(int delta) {
        return getWrapped().isStatusEnabled(delta);
    }

    public void _status(int delta, Throwable e, Object message) {
        getWrapped()._status(delta, e, message);
    }

    public boolean isProgressEnabled() {
        return getWrapped().isProgressEnabled();
    }

    public boolean isProgressEnabled(int delta) {
        return getWrapped().isProgressEnabled(delta);
    }

    public void _progress(int delta, Throwable e, Object message) {
        getWrapped()._progress(delta, e, message);
    }

}
