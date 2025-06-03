package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule0;

public interface IDataTypedTarget2KeyPatternMakeRule0<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends IKeyPatternLikeMakeRule0<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT>,
                IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] {  };
    }

}
