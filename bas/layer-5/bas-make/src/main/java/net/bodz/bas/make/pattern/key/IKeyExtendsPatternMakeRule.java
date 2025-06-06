package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule;
import net.bodz.bas.make.type.IKeyExtendsPattern;

public interface IKeyExtendsPatternMakeRule<Tp extends IKeyExtendsPattern<Param, K>, Param, K, T extends IKeyData<K, TT>, TT>
        extends IKeyPatternLikeMakeRule<Tp, Param, K, IParameterizedKey<?, ?>, T, TT> {

}
