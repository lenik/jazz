package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.meta.decl.NotNull;

public class Capture<Param>
        implements IParameterizedKeys<Param, Param> {

    final Class<? extends Param> parameterType;

    public Capture(@NotNull Class<? extends Param> parameterType) {
        this.parameterType = parameterType;
    }

    @NotNull
    @Override
    public Class<? extends Param> getKeyType() {
        return parameterType;
    }

    @NotNull
    @Override
    public Class<? extends Param> getParameterType() {
        return parameterType;
    }

    @Override
    public Param getKey(Object parameter) {
        @SuppressWarnings("unchecked")
        Param param = (Param) parameter;
        return param;
    }

}
