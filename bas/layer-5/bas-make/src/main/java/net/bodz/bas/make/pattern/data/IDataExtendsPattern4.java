package net.bodz.bas.make.pattern.data;

import net.bodz.bas.make.IParameterType;

public interface IDataExtendsPattern4<Param, T, T1, T2, T3, T4>
        extends IDataExtendsPattern<Param, T>,
                IParameterType<Param> {

    @Override
    default Class<?>[] getInterfaces() {
        return new Class<?>[] { getType1(), getType2(), getType3(), getType4() };
    }

    @SuppressWarnings("unchecked")
    @Override
    default Param match(Object instance) {
        T1 type1 = (T1) instance;
        T2 type2 = (T2) instance;
        T3 type3 = (T3) instance;
        T4 type4 = (T4) instance;
        return matchTyped(type1, type2, type3, type4);
    }

    Class<T1> getType1();
    Class<T2> getType2();
    Class<T3> getType3();
    Class<T4> getType4();

    Param matchTyped(T1 type1, T2 type2, T3 type3, T4 type4);

}
