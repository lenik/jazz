package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.fn.Function1;
import net.bodz.bas.meta.decl.NotNull;

public class SimpleKeyExtendsPattern1<Param, K, T1>
        implements IKeyExtendsPattern1<Param, K, T1> {

    final Class<Param> parameterType;
    Class<? extends K> keyType;
    final Class<T1> type1;
    final Function1<T1, Param> matchFn;

    public SimpleKeyExtendsPattern1(Class<Param> parameterType, Class<T1> type1, Function1<T1, Param> matchFn) {
        this.parameterType = parameterType;
        this.type1 = type1;
        this.matchFn = matchFn;
    }

    public SimpleKeyExtendsPattern1(Class<Param> parameterType, Class<? extends K> keyType, Class<T1> type1, Function1<T1, Param> matchFn) {
        this.parameterType = parameterType;
        this.keyType = keyType;
        this.type1 = type1;
        this.matchFn = matchFn;
    }

    @NotNull
    @Override
    public Class<Param> getParameterType() {
        return parameterType;
    }

    @Override
    @NotNull
    public Class<? extends K> getKeyType() {
        return keyType;
    }

    @Override
    public Class<T1> getType1() {
        return type1;
    }

    @Override
    public Param matchTyped(T1 type1) {
        return matchFn.apply(type1);
    }

}
