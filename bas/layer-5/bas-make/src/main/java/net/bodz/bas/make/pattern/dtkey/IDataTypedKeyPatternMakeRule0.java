package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule0;

public interface IDataTypedKeyPatternMakeRule0<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        T extends IKeyData<K, TT>, TT> //
        extends IKeyPatternLikeMakeRule0<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, T, TT>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] {  };
    }

}
