package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.meta.decl.NotNull;

public class DataTypedCapture<Param, T>
        implements IDataTypedParameterizedKey<Param, Param, T> {

    final Class<Param> parameterType;
    final Class<? extends T> dataType;

    public DataTypedCapture(@NotNull Class<Param> parameterType, @NotNull Class<? extends T> dataType) {
        this.parameterType = parameterType;
        this.dataType = dataType;
    }

    @NotNull
    @Override
    public Class<? extends Param> getKeyType() {
        return parameterType;
    }

    @NotNull
    @Override
    public Class<Param> getParameterType() {
        return parameterType;
    }

    @Override
    public Param getKey(Object parameter) {
        @SuppressWarnings("unchecked")
        Param param = (Param) parameter;
        return param;
    }

    @NotNull
    @Override
    public Class<? extends T> getDataType() {
        return dataType;
    }

}
