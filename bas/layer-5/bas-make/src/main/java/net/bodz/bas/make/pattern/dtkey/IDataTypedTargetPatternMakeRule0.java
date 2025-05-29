package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule0;

public interface IDataTypedTargetPatternMakeRule0<Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends IKeyPatternLikeMakeRule0<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, T, TT>,
                IDataTypedTargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKeys<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKeys[] {  };
    }

}
