package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.MethodSignatureComparator;
import net.bodz.bas.err.DuplicatedKeyException;

public class MutableMethodMap
        extends AbstractMethodMap {

    Map<MethodSignature, IMethod> map;

    public MutableMethodMap(boolean sorted) {
        map = createMap(sorted);
    }

    protected Map<MethodSignature, IMethod> createMap(boolean sorted) {
        if (sorted)
            return new TreeMap<>(MethodSignatureComparator.getInstance());
        else
            return new LinkedHashMap<>();
    }

    @Override
    public int getMethodCount() {
        return map.size();
    }

    @Override
    public Collection<IMethod> getMethods() {
        return map.values();
    }

    @Override
    public boolean containsMethod(MethodSignature signature) {
        return map.containsKey(signature);
    }

    @Override
    public IMethod getMethod(MethodSignature signature) {
        if (signature == null)
            throw new NullPointerException("signature");
        return map.get(signature);
    }

    public void addMethod(IMethod method) {
        if (method == null)
            throw new NullPointerException("method");

        MethodSignature signature = method.getSignature();

        IMethod existing = map.get(signature);
        if (existing != null)
            throw new DuplicatedKeyException(signature.toString());

        map.put(signature, method);
    }

    public IMethod removeMethod(MethodSignature signature) {
        if (signature == null)
            throw new NullPointerException("signature");
        return map.remove(signature);
    }

}
