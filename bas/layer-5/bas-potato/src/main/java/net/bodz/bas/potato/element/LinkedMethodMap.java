package net.bodz.bas.potato.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.t.iterator.Iterables;

public class LinkedMethodMap
        implements IMethodMap {

    private List<IMethodMap> maps;

    public LinkedMethodMap(List<IMethodMap> methodMaps) {
        if (methodMaps == null)
            throw new NullPointerException("methodMaps");
        this.maps = methodMaps;
    }

    public LinkedMethodMap(IMethodMap... methodMaps) {
        maps = Arrays.asList(methodMaps);
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
        List<Iterable<IMethod>> iterables = new ArrayList<Iterable<IMethod>>(maps.size());

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
