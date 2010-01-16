package net.bodz.bas.commons.scripting.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

public class Members {

    public static class PublicMethods extends PrefetchedIterator<Method> {

        private final Method[] methods;
        private int index;

        public PublicMethods(Class<?> clazz) {
            this.methods = clazz.getMethods();
        }

        @Override
        protected Method fetch() {
            for (; index < methods.length;) {
                Method m = methods[index++];
                if (accept(m))
                    return m;
            }
            return end();
        }

        protected boolean accept(Method m) {
            return true;
        }

    }

    public static Iterable<Method> publicMethods(final Class<?> clazz) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new PublicMethods(clazz);
            }
        };
    }

    /**
     * @param stopClass
     *            exclude methods from stop-class and its super classes.
     */
    public static Iterable<Method> publicMethods(final Class<?> clazz, final Class<?> stopClass) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new PublicMethods(clazz) {
                    @Override
                    protected boolean accept(Method m) {
                        boolean superOfStop = m.getDeclaringClass().isAssignableFrom(stopClass);
                        return !superOfStop;
                    }
                };
            }
        };
    }

    public static Iterable<Method> publicMethods(final Class<?> clazz, final String methodName) {
        assert methodName != null;
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new PublicMethods(clazz) {
                    @Override
                    protected boolean accept(Method m) {
                        return methodName.equals(m.getName());
                    }
                };
            }
        };
    }

    public static class AllMethods extends PrefetchedIterator<Method> {
        private Class<?> clazz;
        private Method[] methods;
        private int index;

        public AllMethods(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        protected Method fetch() {
            for (; clazz != null; clazz = nextClass(clazz), methods = null) {
                if (methods == null) {
                    methods = clazz.getDeclaredMethods();
                    index = 0;
                }
                for (; index < methods.length;) {
                    Method m = methods[index++];
                    if (accept(m))
                        return m;
                }
            }
            return end();
        }

        protected Class<?> nextClass(Class<?> clazz) {
            return clazz.getSuperclass();
        }

        protected boolean accept(Method m) {
            return true;
        }

    }

    public static Iterable<Method> allMethods(final Class<?> clazz, final boolean allTree) {
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new AllMethods(clazz) {
                    @Override
                    protected Class<?> nextClass(Class<?> clazz) {
                        return allTree ? clazz.getSuperclass() : null;
                    }
                };
            }
        };
    }

    public static Iterable<Method> allMethods(final Class<?> clazz) {
        return allMethods(clazz, false);
    }

    public static Iterable<Method> allMethods(final Class<?> clazz, final String methodName, final boolean allTree) {
        assert methodName != null;
        return new Iterable<Method>() {
            @Override
            public Iterator<Method> iterator() {
                return new AllMethods(clazz) {
                    @Override
                    protected Class<?> nextClass(Class<?> clazz) {
                        return allTree ? clazz.getSuperclass() : null;
                    }

                    @Override
                    protected boolean accept(Method m) {
                        return methodName.equals(m.getName());
                    }
                };
            }
        };
    }

    public static Iterable<Method> allMethods(final Class<?> clazz, final String methodName) {
        return allMethods(clazz, methodName, false);
    }

    public static Iterable<Method> methods(final Class<?> clazz, boolean all) {
        return all ? allMethods(clazz) : publicMethods(clazz);
    }

    public static Iterable<Method> methods(final Class<?> clazz, final String methodName, boolean all) {
        return all ? allMethods(clazz, methodName) : publicMethods(clazz, methodName);
    }

    public static class PublicConstructors extends PrefetchedIterator<Constructor<?>> {

        private final Constructor<?>[] ctors;
        private int index;

        public PublicConstructors(Class<?> clazz) {
            this.ctors = clazz.getConstructors();
        }

        @Override
        protected Constructor<?> fetch() {
            for (; index < ctors.length;) {
                Constructor<?> ctor = ctors[index++];
                if (accept(ctor))
                    return ctor;
            }
            return end();
        }

        protected boolean accept(Constructor<?> ctor) {
            return true;
        }

    }

    public static Iterable<Constructor<?>> publicConstructors(final Class<?> clazz) {
        return new Iterable<Constructor<?>>() {
            @Override
            public Iterator<Constructor<?>> iterator() {
                return new PublicConstructors(clazz);
            }
        };
    }

    public static class AllConstructors extends PrefetchedIterator<Constructor<?>> {
        private Constructor<?>[] ctors;
        private int index;

        public AllConstructors(Class<?> clazz) {
            ctors = clazz.getDeclaredConstructors();
        }

        @Override
        protected Constructor<?> fetch() {
            for (; index < ctors.length;) {
                Constructor<?> ctor = ctors[index++];
                if (accept(ctor))
                    return ctor;
            }
            return end();
        }

        protected boolean accept(Constructor<?> m) {
            return true;
        }

    }

    public static Iterable<Constructor<?>> allConstructors(final Class<?> clazz) {
        return new Iterable<Constructor<?>>() {
            @Override
            public Iterator<Constructor<?>> iterator() {
                return new AllConstructors(clazz);
            }
        };
    }

    public static Iterable<Constructor<?>> constructors(final Class<?> clazz, boolean all) {
        return all ? allConstructors(clazz) : publicConstructors(clazz);
    }

    public static class PublicFields extends PrefetchedIterator<Field> {

        private final Field[] fields;
        private int index;

        public PublicFields(Class<?> clazz) {
            this.fields = clazz.getFields();
        }

        @Override
        protected Field fetch() {
            for (; index < fields.length;) {
                Field f = fields[index++];
                if (accept(f))
                    return f;
            }
            return end();
        }

        protected boolean accept(Field f) {
            return true;
        }

    }

    public static Iterable<Field> publicFields(final Class<?> clazz) {
        return new Iterable<Field>() {
            @Override
            public Iterator<Field> iterator() {
                return new PublicFields(clazz);
            }
        };
    }

    public static Iterable<Field> publicFields(final Class<?> clazz, final String fieldName) {
        assert fieldName != null;
        return new Iterable<Field>() {
            @Override
            public Iterator<Field> iterator() {
                return new PublicFields(clazz) {
                    @Override
                    protected boolean accept(Field f) {
                        return fieldName.equals(f.getName());
                    }
                };
            }
        };
    }

    public static class AllFields extends PrefetchedIterator<Field> {
        private Class<?> clazz;
        private Field[] fields;
        private int index;

        public AllFields(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        protected Field fetch() {
            for (; clazz != null; clazz = nextClass(clazz), fields = null) {
                if (fields == null) {
                    fields = clazz.getDeclaredFields();
                    index = 0;
                }
                for (; index < fields.length;) {
                    Field f = fields[index++];
                    if (accept(f))
                        return f;
                }
            }
            return end();
        }

        protected Class<?> nextClass(Class<?> clazz) {
            return clazz.getSuperclass();
        }

        protected boolean accept(Field f) {
            return true;
        }

    }

    public static Iterable<Field> allFields(final Class<?> clazz, final boolean allTree) {
        return new Iterable<Field>() {
            @Override
            public Iterator<Field> iterator() {
                return new AllFields(clazz) {
                    @Override
                    protected Class<?> nextClass(Class<?> clazz) {
                        return allTree ? clazz.getSuperclass() : null;
                    }
                };
            }
        };
    }

    public static Iterable<Field> allFields(final Class<?> clazz) {
        return allFields(clazz, false);
    }

    public static Iterable<Field> allFields(final Class<?> clazz, final String fieldName, final boolean allTree) {
        assert fieldName != null;
        return new Iterable<Field>() {
            @Override
            public Iterator<Field> iterator() {
                return new AllFields(clazz) {
                    @Override
                    protected Class<?> nextClass(Class<?> clazz) {
                        return allTree ? clazz.getSuperclass() : null;
                    }

                    @Override
                    protected boolean accept(Field f) {
                        return fieldName.equals(f.getName());
                    }
                };
            }
        };
    }

    public static Iterable<Field> allFields(final Class<?> clazz, final String fieldName) {
        return allFields(clazz, fieldName, false);
    }

    public static Iterable<Field> fields(final Class<?> clazz, boolean all) {
        return all ? allFields(clazz) : publicFields(clazz);
    }

    public static Iterable<Field> fields(final Class<?> clazz, final String fieldName, boolean all) {
        return all ? allFields(clazz, fieldName) : publicFields(clazz, fieldName);
    }

    public static Method findDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        while (clazz != null) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                return method;
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

}
