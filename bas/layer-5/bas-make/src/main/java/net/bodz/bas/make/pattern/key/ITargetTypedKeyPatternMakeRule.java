package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;

public interface ITargetTypedKeyPatternMakeRule<//
        Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, TK, IParameterizedKey<?, ?>, T, TT> {

}
