package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IDataType;
import net.bodz.bas.make.IKeyType;
import net.bodz.bas.make.IParameterType;
import net.bodz.bas.make.IParameterizedKeys;

public interface IDataTypedParameterizedKeys<Param, K, T>
        extends IKeyType<K>,
                IParameterType<Param>,
                IDataType<T>,
                IParameterizedKeys<Param, K> {

}
