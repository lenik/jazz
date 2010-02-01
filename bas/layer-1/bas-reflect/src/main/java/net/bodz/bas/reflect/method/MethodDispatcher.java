package net.bodz.bas.reflect.method;

import java.lang.reflect.Method;
import java.util.Collection;

import net.bodz.bas.reflect.members.FindMethods;
import net.bodz.bas.type.util.TypeDistance;

public class MethodDispatcher {

    private final Collection<Method> methods;

    /**
     * @see FindMethods
     */
    public MethodDispatcher(Collection<Method> methodsWithSameName) {
        if (methodsWithSameName == null)
            throw new NullPointerException("methodsWithSameName");
        this.methods = methodsWithSameName;
    }

    /**
     * @return <code>null</code> If no matching method, or the nearest method whose parameter types
     *         are most closely to the specified <code>parameterTypes</code>.
     */
    public Method getMethod(Class<?>... parameterTypes) {
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        Method nearestMethod = null;
        int nearestDist = Integer.MAX_VALUE;
        for (Method method : methods) {
            Class<?>[] v = method.getParameterTypes();
            int dist = TypeDistance.dist(v, parameterTypes);
            if (dist == -1)
                continue;
            if (dist < nearestDist) {
                nearestDist = dist;
                nearestMethod = method;
            }
        }
        return nearestMethod;
    }

}
