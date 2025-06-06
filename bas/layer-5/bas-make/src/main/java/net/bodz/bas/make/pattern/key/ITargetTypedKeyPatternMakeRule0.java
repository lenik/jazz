package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule0;

public interface ITargetTypedKeyPatternMakeRule0<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends IKeyPatternLikeMakeRule0<Tp, Param, TK, IParameterizedKey<?, ?>, T, TT>,
                ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] {  };
    }

}
