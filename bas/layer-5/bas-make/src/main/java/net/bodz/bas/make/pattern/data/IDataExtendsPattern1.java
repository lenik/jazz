package net.bodz.bas.make.pattern.data;

import net.bodz.bas.make.IParameterType;

public interface IDataExtendsPattern1<Param, T, T1>
        extends IDataExtendsPattern<Param, T>,
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
