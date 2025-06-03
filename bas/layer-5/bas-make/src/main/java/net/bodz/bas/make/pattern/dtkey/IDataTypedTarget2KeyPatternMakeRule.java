package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;

public interface IDataTypedTarget2KeyPatternMakeRule<//
        Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        T extends IKeyData<TK, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, T, TT> {

}
