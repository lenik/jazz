package net.bodz.bas.potato.spi.builtin;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.model.IConstructor;
import net.bodz.bas.potato.model.IConstructorMap;

public class NullConstructorMap
        implements IConstructorMap {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IConstructor> getConstructors() {
        return Collections.emptyList();
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
