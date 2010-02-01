package net.bodz.bas.reflect.members;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.collection.iterator.RepeatIterable;
import net.bodz.bas.reflect.util.MethodSignature;

public class FindMethods {

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public static Iterable<Method> findMethods(Class<?> javaClass, MethodSignature signature, FindMemberOptions options) {
        if (javaClass == null)
            throw new NullPointerException("javaClass");
        if (options.isGetDeclaredMembers()) {
            Class<?> c = javaClass;
            Class<?> stopClass = options.getStopClass();
            if (stopClass == null)
                stopClass = Object.class;
            while (c != null) {
                if (signature.hasNullParameterType()) {

                } else {
                    Method strictlyMatchedDeclaredMethod = c.getDeclaredMethod(signature.getMethodName());
                }
                c = c.getSuperclass();
            }
        } else {
            if (signature.hasNullParameterType()) {
                Method[] publicMethods = javaClass.getMethods();
                List<Method> buf = new ArrayList<Method>(publicMethods.length);
                for (Method m : javaClass.getMethods()) {
                    // signature.
                }
            } else {
                Method strictlyMatchedMethod = signature.matchMethodStrictly(javaClass);
                if (strictlyMatchedMethod == null)
                    return Collections.emptyList();
                else
                    return new RepeatIterable<Method>(strictlyMatchedMethod, 1);
            }
        }
    }
}
