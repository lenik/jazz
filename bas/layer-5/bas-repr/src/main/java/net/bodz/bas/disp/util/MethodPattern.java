package net.bodz.bas.disp.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;

public class MethodPattern {

    private final Class<?>[] pattern;

    public MethodPattern(Class<?>... pattern) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern;
    }

    public Map<String, ClassMethod> searchOverlayMethods(Class<?> clazz, String extension) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (extension == null)
            throw new NullPointerException("extension");

        Map<String, ClassMethod> all = new HashMap<String, ClassMethod>();
        Map<String, ClassMethod> each = new HashMap<String, ClassMethod>();

        while (clazz != null) {
            Class<?> overlay = OverlayUtil.getOverlay(clazz, extension);

            if (overlay != null) {
                searchMethods(each, false, overlay);

                for (Entry<String, ClassMethod> entry : each.entrySet()) {
                    String methodName = entry.getKey();

                    // Don't overwrite the methods defined in subclass
                    if (all.containsKey(methodName))
                        continue;

                    all.put(methodName, entry.getValue());
                }
                each.clear();
            }

            clazz = clazz.getSuperclass();
        }

        return all;
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

    static boolean isAssignable(Class<?>[] lv, Class<?>[] rv) {
        if (lv.length != rv.length)
            return false;

        for (int i = 0; i < lv.length; i++)
            if (!lv[i].isAssignableFrom(rv[i]))
                return false;

        return true;
    }

}
