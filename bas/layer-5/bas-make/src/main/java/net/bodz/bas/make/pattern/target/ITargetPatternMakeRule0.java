package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule0;

public interface ITargetPatternMakeRule0<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT> //
        extends ITargetPatternLikeMakeRule0<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, T, TT>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedTarget<?, ?, ?, ?>[] getInputs() {
        return new IParameterizedTarget[] {  };
    }

}
