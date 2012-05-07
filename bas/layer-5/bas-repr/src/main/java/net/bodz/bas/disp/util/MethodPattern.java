package net.bodz.bas.disp.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;

public class MethodPattern {

    private final Class<?>[] pattern;

    public MethodPattern(Class<?>... pattern) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern;
    }

    public Map<String, ClassMethod> searchMethods(Class<?> clazz) {
        Map<String, ClassMethod> methods = new HashMap<String, ClassMethod>();
        searchMethods(methods, false, clazz);
        return methods;
    }

    public void searchMethods(Map<String, ClassMethod> all, boolean canOverride, Class<?> clazz) {
        for (Method method : clazz.getMethods()) {
            if (!isAssignable(pattern, method.getParameterTypes()))
                continue;

            String methodName = method.getName();

            if (!canOverride) {
                ClassMethod existing = all.get(method.getName());
                if (existing != null)
                    throw new IllegalUsageException("Ambiguous methods: " + existing + " and " + method);
            }

            all.put(methodName, new ClassMethod(clazz, method));
        }
    }

}
