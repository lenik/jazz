package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule;

public interface IDataTypedTargetPatternMakeRule<//
        Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, IDataTypedParameterizedTarget<?, ?, ?, ?>, T, TT> {

}
