package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule0;

public interface ITargetPatternMakeRule0<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends IKeyPatternLikeMakeRule0<Tp, Param, TK, IParameterizedKeys<?, ?>, T, TT>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys[] {  };
    }

}
