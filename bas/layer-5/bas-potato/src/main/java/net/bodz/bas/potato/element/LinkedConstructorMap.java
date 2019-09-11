package net.bodz.bas.potato.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.t.iterator.Iterables;

public class LinkedConstructorMap
        implements IConstructorMap {

    private List<IConstructorMap> maps;

    public LinkedConstructorMap(List<IConstructorMap> constructorMaps) {
        if (constructorMaps == null)
            throw new NullPointerException("constructorMaps");
        this.maps = constructorMaps;
    }

    public LinkedConstructorMap(IConstructorMap... constructorMaps) {
        maps = Arrays.asList(constructorMaps);
    }

    @Override
    public int getConstructorCount() {
        int count = 0;
        for (IConstructorMap map : maps)
            count += map.getConstructorCount();
        return count;
    }

    @Override
    public Iterable<IConstructor> getConstructors() {
        List<Iterable<IConstructor>> iterables = new ArrayList<Iterable<IConstructor>>(maps.size());

        for (IConstructorMap map : maps)
            iterables.add(map.getConstructors());

        return Iterables.concat(iterables);
    }

    @Override
    public boolean containsConstructor(MethodSignature signature) {
        for (IConstructorMap map : maps)
            if (map.containsConstructor(signature))
                return true;
        return false;
    }

    @Override
    public IConstructor getConstructor(MethodSignature signature) {
        for (IConstructorMap map : maps) {
            IConstructor method = map.getConstructor(signature);
            if (method != null)
                return method;
        }
        return null;
    }

}
