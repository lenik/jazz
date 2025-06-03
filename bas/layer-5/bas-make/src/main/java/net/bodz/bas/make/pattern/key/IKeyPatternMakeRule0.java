package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule0;

public interface IKeyPatternMakeRule0<Tp extends IKeyPattern<Param, K>, Param, K, //
        T extends IKeyData<K, TT>, TT> //
        extends IKeyPatternLikeMakeRule0<Tp, Param, K, IParameterizedKey<?, ?>, T, TT>,
                IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] {  };
    }

}
