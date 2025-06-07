package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IParameterType;

public interface IKeyExtendsPattern1<Param, K, T1>
        extends IKeyExtendsPattern<Param, K>,
                IParameterType<Param> {

    @Override
    default Class<?>[] getInterfaces() {
        return new Class<?>[] { getType1() };
    }

    @SuppressWarnings("unchecked")
    @Override
    default Param match(Object instance) {
        T1 type1 = (T1) instance;
        return matchTyped(type1);
    }

    Class<T1> getType1();

    Param matchTyped(T1 type1);

}
