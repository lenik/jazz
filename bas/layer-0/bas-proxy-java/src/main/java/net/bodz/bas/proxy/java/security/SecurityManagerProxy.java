package net.bodz.bas.proxy.java.security;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

import net.bodz.bas.t.model.IWrapper;

public class SecurityManagerProxy
        extends SecurityManager
        implements IWrapper<SecurityManager> {

    protected final SecurityManager _orig;

    /**
     * @param _orig
     *            <code>null</code> if no check at all.
     */
    public SecurityManagerProxy(SecurityManager _orig) {
        this._orig = _orig;
    }

    @Override
    public SecurityManager getWrapped() {
        return _orig;
    }

    @Override
    public void checkAccept(String host, int port) {
        if (_orig == null)
            return;
        else
            _orig.checkAccept(host, port);
    }

    @Override
    public void checkAccess(Thread t) {
        if (_orig == null)
            return;
        else
            _orig.checkAccess(t);
    }

    @Override
    public void checkAccess(ThreadGroup g) {
        if (_orig == null)
            return;
        else
            _orig.checkAccess(g);
    }

    @Override
    public void checkAwtEventQueueAccess() {
        if (_orig == null)
            return;
        else
            _orig.checkAwtEventQueueAccess();
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        if (_orig == null)
            return;
        else
            _orig.checkConnect(host, port, context);
    }

    @Override
    public void checkConnect(String host, int port) {
        if (_orig == null)
            return;
        else
            _orig.checkConnect(host, port);
    }

    @Override
    public void checkCreateClassLoader() {
        if (_orig == null)
            return;
        else
            _orig.checkCreateClassLoader();
    }

    @Override
    public void checkDelete(String file) {
        if (_orig == null)
            return;
        else
            _orig.checkDelete(file);
    }

    @Override
    public void checkExec(String cmd) {
        if (_orig == null)
            return;
        else
            _orig.checkExec(cmd);
    }

    @Override
    public void checkExit(int status) {
        if (_orig == null)
            return;
        else
            _orig.checkExit(status);
    }

    @Override
    public void checkLink(String lib) {
        if (_orig == null)
            return;
        else
            _orig.checkLink(lib);
    }

    @Override
    public void checkListen(int port) {
        if (_orig == null)
            return;
        else
            _orig.checkListen(port);
    }

    @Override
    public void checkMemberAccess(Class<?> clazz, int which) {
        if (_orig == null)
            return;
        else
            _orig.checkMemberAccess(clazz, which);
    }

    @Deprecated
    @Override
    public void checkMulticast(InetAddress maddr, byte ttl) {
        if (_orig == null)
            return;
        else
            _orig.checkMulticast(maddr, ttl);
    }

    @Override
    public void checkMulticast(InetAddress maddr) {
        if (_orig == null)
            return;
        else
            _orig.checkMulticast(maddr);
    }

    @Override
    public void checkPackageAccess(String pkg) {
        if (_orig == null)
            return;
        else
            _orig.checkPackageAccess(pkg);
    }

    @Override
    public void checkPackageDefinition(String pkg) {
        if (_orig == null)
            return;
        else
            _orig.checkPackageDefinition(pkg);
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        if (_orig == null)
            return;
        else
            _orig.checkPermission(perm, context);
    }

    @Override
    public void checkPermission(Permission perm) {
        if (_orig == null)
            return;
        else
            _orig.checkPermission(perm);
    }

    @Override
    public void checkPrintJobAccess() {
        if (_orig == null)
            return;
        else
            _orig.checkPrintJobAccess();
    }

    @Override
    public void checkPropertiesAccess() {
        if (_orig == null)
            return;
        else
            _orig.checkPropertiesAccess();
    }

    @Override
    public void checkPropertyAccess(String key) {
        if (_orig == null)
            return;
        else
            _orig.checkPropertyAccess(key);
    }

    @Override
    public void checkRead(FileDescriptor fd) {
        if (_orig == null)
            return;
        else
            _orig.checkRead(fd);
    }

    @Override
    public void checkRead(String file, Object context) {
        if (_orig == null)
            return;
        else
            _orig.checkRead(file, context);
    }

    @Override
    public void checkRead(String file) {
        if (_orig == null)
            return;
        else
            _orig.checkRead(file);
    }

    @Override
    public void checkSecurityAccess(String target) {
        if (_orig == null)
            return;
        else
            _orig.checkSecurityAccess(target);
    }

    @Override
    public void checkSetFactory() {
        if (_orig == null)
            return;
        else
            _orig.checkSetFactory();
    }

    @Override
    public void checkSystemClipboardAccess() {
        if (_orig == null)
            return;
        else
            _orig.checkSystemClipboardAccess();
    }

    @Override
    public boolean checkTopLevelWindow(Object window) {
        if (_orig == null)
            return true;
        else
            return _orig.checkTopLevelWindow(window);
    }

    @Override
    public void checkWrite(FileDescriptor fd) {
        if (_orig == null)
            return;
        else
            _orig.checkWrite(fd);
    }

    @Override
    public void checkWrite(String file) {
        if (_orig == null)
            return;
        else
            _orig.checkWrite(file);
    }

    @Deprecated
    @Override
    public boolean getInCheck() {
        if (_orig == null)
            return false;
        else
            return _orig.getInCheck();
    }

    @Override
    public Object getSecurityContext() {
        if (_orig == null)
            return super.getSecurityContext();
        else
            return _orig.getSecurityContext();
    }

    @Override
    public ThreadGroup getThreadGroup() {
        if (_orig == null)
            return super.getThreadGroup();
        else
            return _orig.getThreadGroup();
    }

}
