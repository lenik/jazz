package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;

public interface IDataTypedTargetPatternMakeRule<//
        Tp extends IDataTypedTargetPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, TK, IDataTypedParameterizedKeys<?, ?, ?>, T, TT> {

}
