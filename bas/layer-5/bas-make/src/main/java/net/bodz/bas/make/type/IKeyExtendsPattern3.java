package net.bodz.bas.make.type;

import net.bodz.bas.make.IParameterType;

public interface IKeyExtendsPattern3<Param, K, T1, T2, T3>
        extends IKeyExtendsPattern<Param, K>,
                IParameterType<Param> {

    @Override
    default Class<?>[] getInterfaces() {
        return new Class<?>[] { getType1(), getType2(), getType3() };
    }

    @SuppressWarnings("unchecked")
    @Override
    default Param match(Object instance) {
        T1 type1 = (T1) instance;
        T2 type2 = (T2) instance;
        T3 type3 = (T3) instance;
        return matchTyped(type1, type2, type3);
    }

    Class<T1> getType1();
    Class<T2> getType2();
    Class<T3> getType3();

    Param matchTyped(T1 type1, T2 type2, T3 type3);

}
