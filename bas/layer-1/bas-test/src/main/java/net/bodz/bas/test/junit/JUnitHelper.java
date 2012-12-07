package net.bodz.bas.test.junit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.util.annotation.AnnotationUtil;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class JUnitHelper {

    static interface Callback<T> {

        T callback()
                throws Throwable;

    }

    public static <T> T doJUnitLifecycle(Object instance, Callback<T> callback)
            throws Throwable {

        if (instance == null)
            throw new NullPointerException("instance");

        Class<? extends Object> clazz = instance.getClass();

        T retval;

        try {

            beforeClass(clazz);
            beforeMethod(clazz, instance);

            retval = callback.callback();

            afterMethod(clazz, instance);
            afterClass(clazz);

        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(e);
        }

        return retval;
    }

    static <A extends Annotation> boolean filter(Method method, boolean staticCheck, Class<A> annotation) {

        int modifiers = method.getModifiers();

        if (staticCheck)
            if (!Modifier.isStatic(modifiers))
                return false;

        // TODO - JUnit allow non-public @BeforeClass??
        if (!Modifier.isPublic(modifiers))
            return false;

        A an = AnnotationUtil.getDeclaredAnnotation(method, annotation);
        if (an == null)
            return false;

        int params = method.getParameterTypes().length;
        if (params != 0)
            throw new IllegalUsageException("@" + annotation.getSimpleName() + " method should not have any parameter.");

// if (logger.isTraceEnabled())
// logger.trace("Filtered: @" + annotation.getSimpleName() + " " +
// method.getDeclaringClass().getSimpleName()
// + "::" + method.getName());

        return true;
    }

    static Set<Class<?>> initializedClasses = new HashSet<Class<?>>();

    static synchronized void beforeClass(Class<?> clazz)
            throws ReflectiveOperationException {

        if (initializedClasses.contains(clazz))
            return;

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            beforeClass(superclass);

        for (Method method : clazz.getDeclaredMethods()) {
            if (filter(method, true, BeforeClass.class))
                method.invoke(null);
        }

        initializedClasses.add(clazz);
    }

    static synchronized void afterClass(Class<?> clazz)
            throws ReflectiveOperationException {

        if (!initializedClasses.contains(clazz))
            return;

        for (Method method : clazz.getDeclaredMethods()) {
            if (filter(method, true, AfterClass.class))
                method.invoke(null);
        }

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            afterClass(superclass);

        initializedClasses.remove(clazz);
    }

    static void beforeMethod(Class<?> clazz, Object obj)
            throws ReflectiveOperationException {

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            beforeMethod(superclass, obj);

        for (Method method : clazz.getDeclaredMethods()) {
            if (filter(method, false, Before.class))
                method.invoke(obj);
        }
    }

    static void afterMethod(Class<?> clazz, Object obj)
            throws ReflectiveOperationException {

        for (Method method : clazz.getDeclaredMethods()) {
            if (filter(method, false, After.class))
                method.invoke(obj);
        }

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            afterMethod(superclass, obj);

    }

    static class WrapIntereceptor
            implements MethodInterceptor {

        static Set<Class<? extends Annotation>> junitInternalAnnotations;

        static {
            // TODO - typehierset? for interface inheritance?
            junitInternalAnnotations = new HashSet<Class<? extends Annotation>>();
            junitInternalAnnotations.add(BeforeClass.class);
            junitInternalAnnotations.add(AfterClass.class);
            junitInternalAnnotations.add(Before.class);
            junitInternalAnnotations.add(After.class);
        }

        static boolean topLevel = false;

        @Override
        public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy)
                throws Throwable {

            boolean isInternalJunitMethod = false;

            L: for (Annotation ann : method.getAnnotations()) {
                Class<?> annClass = ann.getClass();
                for (Class<?> junitInternal : junitInternalAnnotations)
                    if (junitInternal.isAssignableFrom(annClass)) {
                        isInternalJunitMethod = true;
                        break L;
                    }
            }

            if (isInternalJunitMethod)
                return proxy.invokeSuper(obj, args);

            if (topLevel)
                return proxy.invokeSuper(obj, args);
            else {
                topLevel = true;

                try {
                    return JUnitHelper.doJUnitLifecycle(obj, new Callback<Object>() {

                        public Object callback()
                                throws Throwable {
                            return proxy.invokeSuper(obj, args);
                        }

                    });
                } finally {
                    topLevel = false;
                }
            }
        }

        static final WrapIntereceptor INSTANCE = new WrapIntereceptor();
    }

    /**
     * Instead of generate an ugly "$$EnhancerByCGLIB$$38abe9c5" suffixes,
     */
    static class HelperNamingPolicy
            implements NamingPolicy {

        /**
         * Choose a name for a generated class.
         * 
         * @param prefix
         *            a dotted-name chosen by the generating class (possibly to put the generated
         *            class in a particular package)
         * @param source
         *            the fully-qualified class name of the generating class (for example
         *            "net.sf.cglib.Enhancer")
         * @param key
         *            A key object representing the state of the parameters; for caching to work
         *            properly, equal keys should result in the same generated class name. The
         *            default policy incorporates <code>key.hashCode()</code> into the class name.
         * @param exists
         *            a predicate that returns true if the given classname has already been used in
         *            the same ClassLoader.
         * @return the fully-qualified class name
         */
        @Override
        public String getClassName(String prefix, String source, Object key, Predicate exists) {
            // $$EnhancerByCGLIB$$38abe9c5.
            // EnhancerKey ekey=(EnhancerKey) key;

            String base = prefix + "_JUW_" + Integer.toHexString(key.hashCode());

            String attempt = base;

            int index = 1;
            while (exists.evaluate(attempt))
                attempt = base + "_" + index++;

            return attempt;
        }

    }

    static NamingPolicy namingPolicy = new HelperNamingPolicy();

    public static <T> T createWrappedInstance(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();

        enhancer.setNamingPolicy(namingPolicy);

        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(WrapIntereceptor.INSTANCE);

        Object instance = enhancer.create();

        return targetClass.cast(instance);
    }

}
