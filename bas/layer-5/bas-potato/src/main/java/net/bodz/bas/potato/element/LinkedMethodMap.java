package net.bodz.bas.potato.element;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.t.iterator.Iterables;

public class LinkedMethodMap
        implements IMethodMap {

    private List<IMethodMap> maps = new ArrayList<>();

    public LinkedMethodMap(IMethodMap... methodMaps) {
        for (IMethodMap map : methodMaps)
            maps.add(map);
    }

    @Override
    public int getMethodCount() {
        int count = 0;
        for (IMethodMap map : maps)
            count += map.getMethodCount();
        return count;
    }

    @Override
    public Iterable<IMethod> getMethods() {
        List<Iterable<IMethod>> iterables = new ArrayList<>(maps.size());

        for (IMethodMap map : maps)
            iterables.add(map.getMethods());

        return Iterables.concat(iterables);
    }

    @Override
    public boolean containsMethod(MethodSignature signature) {
        for (IMethodMap map : maps)
            if (map.containsMethod(signature))
                return true;
        return false;
    }

    @Override
    public IMethod getMethod(MethodSignature signature) {
        for (IMethodMap map : maps) {
            IMethod method = map.getMethod(signature);
            if (method != null)
                return method;
        }
        return null;
    }

}
