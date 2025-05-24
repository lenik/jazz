package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;

public interface IKeyPatternMakeRule<Tp extends IKeyPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, K, IParameterizedKeys<?, ?>, T, TT> {

}
