package net.bodz.mda.loader;

import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

public class GroovyCompileClassLoader extends ClassLoader {

    GroovyShell        s;
    GroovyScriptEngine eng;

    public GroovyCompileClassLoader() {
        super();
    }

    public GroovyCompileClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }

}
