package net.bodz.bas.repr.path.builtin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

/**
 * Overloaded method isn't supported.
 */
public class InvocationPathDispatcher
        extends AbstractPathDispatcher {

    @Override
    public int getPriority() {
        return BuiltinPathDispatcherPriorities.PRIORITY_INVOCATION;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String methodName = tokens.peek();
        if (methodName == null)
            return previous;

        IType type = PotatoTypes.getInstance().forClass(obj.getClass());
        IMethod method = type.getMethod("");
        Invocation invocation = new Invocation(obj, method);

        return PathArrival.shift(previous, invocation, tokens);
    }

    static final String CLS_METHOD_MAP_ID;
    static final LazyTypeMap<Map<String, Method>> clsMethodMap;
    static {
        clsMethodMap = TypeMapRegistry.createMap(new EntryLoader());
        CLS_METHOD_MAP_ID = clsMethodMap.getRegisteredId();
    }

    static class EntryLoader
            implements IMapEntryLoader<Class<?>, Map<String, Method>> {

        @Override
        public Map<String, Method> loadValue(Class<?> type)
                throws LazyLoadException {
            Map<String, Method> methodMap = new HashMap<String, Method>();

            Method[] methods = type.getMethods();
            Set<String> overloaded = new HashSet<String>();
            for (Method m : methods) {
                String name = m.getName();
                if (!overloaded.add(name))
                    continue;
                else
                    methodMap.put(name, m);
            }

            for (String name : overloaded)
                methodMap.remove(name);

            return methodMap;
        }

    } // EntryLoader

}
