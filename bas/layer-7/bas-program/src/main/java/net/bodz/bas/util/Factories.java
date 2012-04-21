package net.bodz.bas.util;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.mode.fn.AbstractFactory;
import net.bodz.bas.xml.XMLs;

public interface Factories {

    class Ctor<T>
            extends AbstractFactory<T> {

        private Class<? extends T> clazz;
        private Object outer;

        public Ctor(Class<? extends T> clazz, Object outer) {
            assert clazz != null;
            if (clazz.isMemberClass()) {
                if (outer == null)
                    throw new NullPointerException("no outer specified for member " + clazz);
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
        public T _create(Class<?>[] argTypes, Object... args)
                throws CreateException {
            try {
                if (clazz.isMemberClass())
                    // return CompatMethods.newMemberInstance(clazz, outer, args);
                    throw new NotImplementedException();
                else
                    // return CompatMethods.newInstance(clazz, args);
                    throw new NotImplementedException();
            } catch (Exception e) {
                throw new CreateException(e.getMessage(), e);
            }
        }

    }

    class ByClassName
            extends AbstractFactory<Object> {

        private final ClassLoader loader;
        private final String name;

        public ByClassName(ClassLoader loader, String name) {
            if (loader == null)
                throw new NullPointerException("classLoader");
            if (name == null)
                throw new NullPointerException("className");
            this.loader = loader;
            this.name = name;
        }

        @Override
        public Class<? extends Object> getType() {
            return Object.class;
        }

        @Override
        public Object _create(Class<?>[] argTypes, Object... args)
                throws CreateException {
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

    class ByXML
            extends AbstractFactory<Object> {

        // private ClassLoader loader;
        String xml;
        ExceptionListener listener;

        public ByXML(String xml, ExceptionListener listener) {
            if (xml == null)
                throw new NullPointerException("xml");
            this.xml = xml;
            this.listener = listener;
        }

        @Override
        public Class<? extends Object> getType() {
            return Object.class;
        }

        @Override
        public Object _create(Class<?>[] argTypes, Object... args)
                throws CreateException {
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

    class ByXMLFile
            extends AbstractFactory<Object> {

        // private ClassLoader loader;
        File xmlFile;
        ExceptionListener listener;

        public ByXMLFile(File xmlFile, ExceptionListener listener) {
            if (xmlFile == null)
                throw new NullPointerException("xmlFile");
            this.xmlFile = xmlFile;
            this.listener = listener;
        }

        @Override
        public Class<? extends Object> getType() {
            return Object.class;
        }

        @Override
        public Object _create(Class<?>[] argTypes, Object... args)
                throws CreateException {
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
