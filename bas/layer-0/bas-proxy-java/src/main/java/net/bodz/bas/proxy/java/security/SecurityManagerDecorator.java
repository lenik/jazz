package net.bodz.bas.proxy.java.security;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

import net.bodz.bas.api.proxy.IDecorator;

public class SecurityManagerDecorator
        extends SecurityManager
        implements IDecorator<SecurityManager> {

    protected final SecurityManager impl;

    /**
     * @param impl
     *            <code>null</code> if no check at all.
     */
    public SecurityManagerDecorator(SecurityManager impl) {
        this.impl = impl;
    }

    @Override
    public SecurityManager getImplementation() {
        return impl;
    }

    @Override
    public void checkAccept(String host, int port) {
        if (impl == null)
            return;
        else
            impl.checkAccept(host, port);
    }

    @Override
    public void checkAccess(Thread t) {
        if (impl == null)
            return;
        else
            impl.checkAccess(t);
    }

    @Override
    public void checkAccess(ThreadGroup g) {
        if (impl == null)
            return;
        else
            impl.checkAccess(g);
    }

    @Override
    public void checkAwtEventQueueAccess() {
        if (impl == null)
            return;
        else
            impl.checkAwtEventQueueAccess();
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        if (impl == null)
            return;
        else
            impl.checkConnect(host, port, context);
    }

    @Override
    public void checkConnect(String host, int port) {
        if (impl == null)
            return;
        else
            impl.checkConnect(host, port);
    }

    @Override
    public void checkCreateClassLoader() {
        if (impl == null)
            return;
        else
            impl.checkCreateClassLoader();
    }

    @Override
    public void checkDelete(String file) {
        if (impl == null)
            return;
        else
            impl.checkDelete(file);
    }

    @Override
    public void checkExec(String cmd) {
        if (impl == null)
            return;
        else
            impl.checkExec(cmd);
    }

    @Override
    public void checkExit(int status) {
        if (impl == null)
            return;
        else
            impl.checkExit(status);
    }

    @Override
    public void checkLink(String lib) {
        if (impl == null)
            return;
        else
            impl.checkLink(lib);
    }

    @Override
    public void checkListen(int port) {
        if (impl == null)
            return;
        else
            impl.checkListen(port);
    }

    @Override
    public void checkMemberAccess(Class<?> clazz, int which) {
        if (impl == null)
            return;
        else
            impl.checkMemberAccess(clazz, which);
    }

    @Deprecated
    @Override
    public void checkMulticast(InetAddress maddr, byte ttl) {
        if (impl == null)
            return;
        else
            impl.checkMulticast(maddr, ttl);
    }

    @Override
    public void checkMulticast(InetAddress maddr) {
        if (impl == null)
            return;
        else
            impl.checkMulticast(maddr);
    }

    @Override
    public void checkPackageAccess(String pkg) {
        if (impl == null)
            return;
        else
            impl.checkPackageAccess(pkg);
    }

    @Override
    public void checkPackageDefinition(String pkg) {
        if (impl == null)
            return;
        else
            impl.checkPackageDefinition(pkg);
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        if (impl == null)
            return;
        else
            impl.checkPermission(perm, context);
    }

    @Override
    public void checkPermission(Permission perm) {
        if (impl == null)
            return;
        else
            impl.checkPermission(perm);
    }

    @Override
    public void checkPrintJobAccess() {
        if (impl == null)
            return;
        else
            impl.checkPrintJobAccess();
    }

    @Override
    public void checkPropertiesAccess() {
        if (impl == null)
            return;
        else
            impl.checkPropertiesAccess();
    }

    @Override
    public void checkPropertyAccess(String key) {
        if (impl == null)
            return;
        else
            impl.checkPropertyAccess(key);
    }

    @Override
    public void checkRead(FileDescriptor fd) {
        if (impl == null)
            return;
        else
            impl.checkRead(fd);
    }

    @Override
    public void checkRead(String file, Object context) {
        if (impl == null)
            return;
        else
            impl.checkRead(file, context);
    }

    @Override
    public void checkRead(String file) {
        if (impl == null)
            return;
        else
            impl.checkRead(file);
    }

    @Override
    public void checkSecurityAccess(String target) {
        if (impl == null)
            return;
        else
            impl.checkSecurityAccess(target);
    }

    @Override
    public void checkSetFactory() {
        if (impl == null)
            return;
        else
            impl.checkSetFactory();
    }

    @Override
    public void checkSystemClipboardAccess() {
        if (impl == null)
            return;
        else
            impl.checkSystemClipboardAccess();
    }

    @Override
    public boolean checkTopLevelWindow(Object window) {
        if (impl == null)
            return true;
        else
            return impl.checkTopLevelWindow(window);
    }

    @Override
    public void checkWrite(FileDescriptor fd) {
        if (impl == null)
            return;
        else
            impl.checkWrite(fd);
    }

    @Override
    public void checkWrite(String file) {
        if (impl == null)
            return;
        else
            impl.checkWrite(file);
    }

    @Deprecated
    @Override
    public boolean getInCheck() {
        if (impl == null)
            return false;
        else
            return impl.getInCheck();
    }

    @Override
    public Object getSecurityContext() {
        if (impl == null)
            return super.getSecurityContext();
        else
            return impl.getSecurityContext();
    }

    @Override
    public ThreadGroup getThreadGroup() {
        if (impl == null)
            return super.getThreadGroup();
        else
            return impl.getThreadGroup();
    }

}
