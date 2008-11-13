package net.bodz.bas.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

public class ResetableClassLoader {

    private URLClassLoader ucl;

    private URL[]          resetUrls;
    private ClassLoader    resetParent;

    public ResetableClassLoader(URL[] urls, ClassLoader parent) {
        this.resetUrls = urls;
        this.resetParent = parent;
        reset();
    }

    public ResetableClassLoader(URL[] urls) {
        this(urls, ClassLoader.getSystemClassLoader());
    }

    public void reset() {
        this.ucl = new URLClassLoader(resetUrls, resetParent);
    }

    public URL[] getResetUrls() {
        return resetUrls;
    }

    public void setResetUrls(URL[] resetUrls) {
        this.resetUrls = resetUrls;
    }

    public ClassLoader getResetParent() {
        return resetParent;
    }

    public void setResetParent(ClassLoader resetParent) {
        this.resetParent = resetParent;
    }

    public URLClassLoader getClassLoader() {
        return ucl;
    }

    public void clearAssertionStatus() {
        ucl.clearAssertionStatus();
    }

    public URL findResource(String name) {
        return ucl.findResource(name);
    }

    public Enumeration<URL> findResources(String name) throws IOException {
        return ucl.findResources(name);
    }

    public final ClassLoader getParent() {
        return ucl.getParent();
    }

    public URL getResource(String name) {
        return ucl.getResource(name);
    }

    public InputStream getResourceAsStream(String name) {
        return ucl.getResourceAsStream(name);
    }

    public Enumeration<URL> getResources(String name) throws IOException {
        return ucl.getResources(name);
    }

    public URL[] getURLs() {
        return ucl.getURLs();
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return ucl.loadClass(name);
    }

    public void setClassAssertionStatus(String className, boolean enabled) {
        ucl.setClassAssertionStatus(className, enabled);
    }

    public void setDefaultAssertionStatus(boolean enabled) {
        ucl.setDefaultAssertionStatus(enabled);
    }

    public void setPackageAssertionStatus(String packageName, boolean enabled) {
        ucl.setPackageAssertionStatus(packageName, enabled);
    }

}
