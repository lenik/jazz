package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.ITargetPatternLike;

public interface ITargetPattern<Param, T extends IKeyData<TK, TT>, TK, TT>
        extends ITargetPatternLike<Param, T, TK, TT> {

}
