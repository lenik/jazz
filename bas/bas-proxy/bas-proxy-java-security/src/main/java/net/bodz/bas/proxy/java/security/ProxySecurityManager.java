package net.bodz.bas.proxy.java.security;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

import net.bodz.bas.api.hint.Proxy;

public class ProxySecurityManager
        extends SecurityManager
        implements Proxy<SecurityManager> {

    protected final SecurityManager proxy;

    public ProxySecurityManager(SecurityManager proxy) {
        if (proxy == null)
            throw new NullPointerException("proxy");
        this.proxy = proxy;
    }

    @Override
    public SecurityManager getProxyTarget() {
        return proxy;
    }

    @Override
    public void checkAccept(String host, int port) {
        if (proxy == null)
            return;
        else
            proxy.checkAccept(host, port);
    }

    @Override
    public void checkAccess(Thread t) {
        if (proxy == null)
            return;
        else
            proxy.checkAccess(t);
    }

    @Override
    public void checkAccess(ThreadGroup g) {
        if (proxy == null)
            return;
        else
            proxy.checkAccess(g);
    }

    @Override
    public void checkAwtEventQueueAccess() {
        if (proxy == null)
            return;
        else
            proxy.checkAwtEventQueueAccess();
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        if (proxy == null)
            return;
        else
            proxy.checkConnect(host, port, context);
    }

    @Override
    public void checkConnect(String host, int port) {
        if (proxy == null)
            return;
        else
            proxy.checkConnect(host, port);
    }

    @Override
    public void checkCreateClassLoader() {
        if (proxy == null)
            return;
        else
            proxy.checkCreateClassLoader();
    }

    @Override
    public void checkDelete(String file) {
        if (proxy == null)
            return;
        else
            proxy.checkDelete(file);
    }

    @Override
    public void checkExec(String cmd) {
        if (proxy == null)
            return;
        else
            proxy.checkExec(cmd);
    }

    @Override
    public void checkExit(int status) {
        if (proxy == null)
            return;
        else
            proxy.checkExit(status);
    }

    @Override
    public void checkLink(String lib) {
        if (proxy == null)
            return;
        else
            proxy.checkLink(lib);
    }

    @Override
    public void checkListen(int port) {
        if (proxy == null)
            return;
        else
            proxy.checkListen(port);
    }

    @Override
    public void checkMemberAccess(Class<?> clazz, int which) {
        if (proxy == null)
            return;
        else
            proxy.checkMemberAccess(clazz, which);
    }

    @Deprecated
    @Override
    public void checkMulticast(InetAddress maddr, byte ttl) {
        if (proxy == null)
            return;
        else
            proxy.checkMulticast(maddr, ttl);
    }

    @Override
    public void checkMulticast(InetAddress maddr) {
        if (proxy == null)
            return;
        else
            proxy.checkMulticast(maddr);
    }

    @Override
    public void checkPackageAccess(String pkg) {
        if (proxy == null)
            return;
        else
            proxy.checkPackageAccess(pkg);
    }

    @Override
    public void checkPackageDefinition(String pkg) {
        if (proxy == null)
            return;
        else
            proxy.checkPackageDefinition(pkg);
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        if (proxy == null)
            return;
        else
            proxy.checkPermission(perm, context);
    }

    @Override
    public void checkPermission(Permission perm) {
        if (proxy == null)
            return;
        else
            proxy.checkPermission(perm);
    }

    @Override
    public void checkPrintJobAccess() {
        if (proxy == null)
            return;
        else
            proxy.checkPrintJobAccess();
    }

    @Override
    public void checkPropertiesAccess() {
        if (proxy == null)
            return;
        else
            proxy.checkPropertiesAccess();
    }

    @Override
    public void checkPropertyAccess(String key) {
        if (proxy == null)
            return;
        else
            proxy.checkPropertyAccess(key);
    }

    @Override
    public void checkRead(FileDescriptor fd) {
        if (proxy == null)
            return;
        else
            proxy.checkRead(fd);
    }

    @Override
    public void checkRead(String file, Object context) {
        if (proxy == null)
            return;
        else
            proxy.checkRead(file, context);
    }

    @Override
    public void checkRead(String file) {
        if (proxy == null)
            return;
        else
            proxy.checkRead(file);
    }

    @Override
    public void checkSecurityAccess(String target) {
        if (proxy == null)
            return;
        else
            proxy.checkSecurityAccess(target);
    }

    @Override
    public void checkSetFactory() {
        if (proxy == null)
            return;
        else
            proxy.checkSetFactory();
    }

    @Override
    public void checkSystemClipboardAccess() {
        if (proxy == null)
            return;
        else
            proxy.checkSystemClipboardAccess();
    }

    @Override
    public boolean checkTopLevelWindow(Object window) {
        if (proxy == null)
            return true;
        else
            return proxy.checkTopLevelWindow(window);
    }

    @Override
    public void checkWrite(FileDescriptor fd) {
        if (proxy == null)
            return;
        else
            proxy.checkWrite(fd);
    }

    @Override
    public void checkWrite(String file) {
        if (proxy == null)
            return;
        else
            proxy.checkWrite(file);
    }

    @Deprecated
    @Override
    public boolean getInCheck() {
        if (proxy == null)
            return false;
        else
            return proxy.getInCheck();
    }

    @Override
    public Object getSecurityContext() {
        if (proxy == null)
            return super.getSecurityContext();
        else
            return proxy.getSecurityContext();
    }

    @Override
    public ThreadGroup getThreadGroup() {
        if (proxy == null)
            return super.getThreadGroup();
        else
            return proxy.getThreadGroup();
    }

}
