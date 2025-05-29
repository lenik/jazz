package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;

public interface ITargetPatternMakeRule<//
        Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, TK, IParameterizedKeys<?, ?>, T, TT> {

}
