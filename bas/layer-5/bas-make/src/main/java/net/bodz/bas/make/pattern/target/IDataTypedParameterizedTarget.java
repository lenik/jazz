package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IDataType;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IKeyType;
import net.bodz.bas.make.IParameterType;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.ITargetType;

public interface IDataTypedParameterizedTarget<Param, T extends IKeyData<TK, TT>, TK, TT>
        extends IParameterType<Param>,
                ITargetType<T, TK, TT>,
                IKeyType<TK>,
                IDataType<TT>,
                IParameterizedTarget<Param, T, TK, TT> {

}
