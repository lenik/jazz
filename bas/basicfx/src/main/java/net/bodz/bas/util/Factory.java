package net.bodz.bas.util;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.DecodeException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.CompatMethods;
import net.bodz.bas.xml.XMLs;

public interface Factory<T> {

    Class<? extends T> getType();

    /**
     * @param argTypes
     *            may be <code>null</code> if not applicable. if argTypes isn't null, its length
     *            must be equals to the number of args.
     * @param args
     *            each argument must be instance of corresponding argType, or <code>null</code>.
     */
    T _create(Class<?>[] argTypes, Object... args) throws CreateException;

    T create(Object... args) throws CreateException;

    T create() throws CreateException;

    class Static<T> extends _Factory<T> {

        private final T instance;

        public Static(T instance) {
            assert instance != null;
            this.instance = instance;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Class<? extends T> getType() {
            return (Class<? extends T>) instance.getClass();
        }

        @Override
        public T _create(Class<?>[] argTypes, Object... args) {
            return instance;
        }

    }

    class Ctor<T> extends _Factory<T> {

        private Class<? extends T> clazz;
        private Object outer;

        public Ctor(Class<? extends T> clazz, Object outer) {
            assert clazz != null;
            if (clazz.isMemberClass()) {
                if (outer == null)
                    throw new NullPointerException(AppNLS.getString("Factory.needEnclosing") + clazz); //$NON-NLS-1$
            }
            this.clazz = clazz;
            this.outer = outer;
        }

        public Ctor(Class<? extends T> clazz) {
            this(clazz, null);
        }

        @Override
        public Class<? extends T> getType() {
            return clazz;
        }

        @Override
        public T _create(Class<?>[] argTypes, Object... args) throws CreateException {
            try {
                if (clazz.isMemberClass())
                    return CompatMethods.newMemberInstance(clazz, outer, args);
                else
                    return CompatMethods.newInstance(clazz, args);
            } catch (Exception e) {
                throw new CreateException(e.getMessage(), e);
            }
        }
    }

    class ByClassName extends _Factory<Object> {

        private final ClassLoader loader;
        private final String name;

        public ByClassName(int caller, String name) {
            this(Caller.getCallerClassLoader(caller + 0), name);
        }

        public ByClassName(ClassLoader loader, String name) {
            if (loader == null)
                throw new NullPointerException("classLoader"); //$NON-NLS-1$
            if (name == null)
                throw new NullPointerException("className"); //$NON-NLS-1$
            this.loader = loader;
            this.name = name;
        }

        @Override
        public Class<? extends Object> getType() {
            return Object.class;
        }

        @Override
        public Object _create(Class<?>[] argTypes, Object... args) throws CreateException {
            try {
                Class<?> clazz = Class.forName(name, true, loader);
                if (argTypes == null)
                    return clazz.newInstance();
                else {
                    Constructor<?> ctor = clazz.getConstructor(argTypes);
                    return ctor.newInstance(args);
                }
            } catch (ClassNotFoundException e) {
                throw new CreateException(e);
            } catch (Exception e) {
                throw new CreateException(e);
            }
        }

    }

    class ByXML extends _Factory<Object> {

        // private ClassLoader loader;
        String xml;
        ExceptionListener listener;

        public ByXML(String xml, ExceptionListener listener) {
            if (xml == null)
                throw new NullPointerException("xml"); //$NON-NLS-1$
            this.xml = xml;
            this.listener = listener;
        }

        @Override
        public Class<? extends Object> getType() {
            return Object.class;
        }

        @Override
        public Object _create(Class<?>[] argTypes, Object... args) throws CreateException {
            if (listener == null)
                try {
                    return XMLs.decode(xml);
                } catch (DecodeException e) {
                    throw new CreateException(e);
                }
            else
                return XMLs.decode(xml, listener);
        }

    }

    class ByXMLFile extends _Factory<Object> {

        // private ClassLoader loader;
        File xmlFile;
        ExceptionListener listener;

        public ByXMLFile(File xmlFile, ExceptionListener listener) {
            if (xmlFile == null)
                throw new NullPointerException("xmlFile"); //$NON-NLS-1$
            this.xmlFile = xmlFile;
            this.listener = listener;
        }

        @Override
        public Class<? extends Object> getType() {
            return Object.class;
        }

        @Override
        public Object _create(Class<?>[] argTypes, Object... args) throws CreateException {
            FileInputStream in = null;
            try {
                in = new FileInputStream(xmlFile);
                if (listener == null)
                    return XMLs.decode(in);
                else
                    return XMLs.decode(in, listener);
            } catch (DecodeException e) {
                throw new CreateException(e);
            } catch (IOException e) {
                throw new CreateException(e);
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                        throw new CreateException(e);
                    }
            }
        }

    }

}
