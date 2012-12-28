package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.c.reflect.MethodSignature;

public class NullConstructorMap
        implements IConstructorMap {

    @Override
    public int getConstructorCount() {
        return 0;
    }

    @Override
    public Collection<IConstructor> getConstructors() {
        return Collections.emptyList();
    }

    @Override
    public boolean containsConstructor(MethodSignature signature) {
        return false;
    }

    @Override
    public IConstructor getConstructor(MethodSignature signature) {
        return null;
    }

    static final NullConstructorMap instance = new NullConstructorMap();

    public static NullConstructorMap getInstance() {
        return instance;
    }

}
