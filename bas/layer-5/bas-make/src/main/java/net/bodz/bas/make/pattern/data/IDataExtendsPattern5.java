package net.bodz.bas.make.pattern.data;

import net.bodz.bas.make.IParameterType;

public interface IDataExtendsPattern5<Param, T, T1, T2, T3, T4, T5>
        extends IDataExtendsPattern<Param, T>,
                IParameterType<Param> {

    @Override
    default Class<?>[] getInterfaces() {
        return new Class<?>[] { getType1(), getType2(), getType3(), getType4(), getType5() };
    }

    @SuppressWarnings("unchecked")
    @Override
    default Param match(Object instance) {
        T1 type1 = (T1) instance;
        T2 type2 = (T2) instance;
        T3 type3 = (T3) instance;
        T4 type4 = (T4) instance;
        T5 type5 = (T5) instance;
        return matchTyped(type1, type2, type3, type4, type5);
    }

    Class<T1> getType1();
    Class<T2> getType2();
    Class<T3> getType3();
    Class<T4> getType4();
    Class<T5> getType5();

    Param matchTyped(T1 type1, T2 type2, T3 type3, T4 type4, T5 type5);

}
