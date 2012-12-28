package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.MethodSignatureComparator;
import net.bodz.bas.err.DuplicatedKeyException;

public class MutableConstructorMap
        implements IConstructorMap {

    Map<MethodSignature, IConstructor> map;

    public MutableConstructorMap() {
        map = new TreeMap<MethodSignature, IConstructor>(MethodSignatureComparator.getInstance());
    }

    @Override
    public int getConstructorCount() {
        return map.size();
    }

    @Override
    public Collection<IConstructor> getConstructors() {
        return map.values();
    }

    @Override
    public boolean containsConstructor(MethodSignature signature) {
        return map.containsKey(signature);
    }

    @Override
    public IConstructor getConstructor(MethodSignature signature) {
        if (signature == null)
            throw new NullPointerException("signature");
        return map.get(signature);
    }

    public void addConstructor(IConstructor ctor) {
        if (ctor == null)
            throw new NullPointerException("ctor");

        MethodSignature signature = ctor.getSignature();

        IConstructor existing = map.get(signature);
        if (existing != null)
            throw new DuplicatedKeyException(signature.toString());

        map.put(signature, ctor);
    }

    public IConstructor removeConstructore(MethodSignature signature) {
        if (signature == null)
            throw new NullPointerException("signature");
        return map.remove(signature);
    }

}
