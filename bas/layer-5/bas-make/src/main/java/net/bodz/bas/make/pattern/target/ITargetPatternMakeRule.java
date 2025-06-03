package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule;

public interface ITargetPatternMakeRule<//
        Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, T, TT> {

}
