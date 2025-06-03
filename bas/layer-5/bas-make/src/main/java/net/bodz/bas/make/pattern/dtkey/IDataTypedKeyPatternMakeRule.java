package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;

public interface IDataTypedKeyPatternMakeRule<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        T extends IKeyData<K, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, T, TT> {

}
