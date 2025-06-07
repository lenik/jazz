package net.bodz.bas.make.pattern.data;

import net.bodz.bas.make.IParameterType;

public interface IDataExtendsPattern2<Param, T, T1, T2>
        extends IDataExtendsPattern<Param, T>,
                IParameterType<Param> {

    @Override
    default Class<?>[] getInterfaces() {
        return new Class<?>[] { getType1(), getType2() };
    }

    @SuppressWarnings("unchecked")
    @Override
    default Param match(Object instance) {
        T1 type1 = (T1) instance;
        T2 type2 = (T2) instance;
        return matchTyped(type1, type2);
    }

    Class<T1> getType1();
    Class<T2> getType2();

    Param matchTyped(T1 type1, T2 type2);

}
