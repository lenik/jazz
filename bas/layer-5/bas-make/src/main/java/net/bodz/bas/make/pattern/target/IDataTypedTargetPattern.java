package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IDataType;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.ITargetType;
import net.bodz.bas.make.pattern.template.ITargetPatternLike;

public interface IDataTypedTargetPattern<Param, T extends IKeyData<TK, TT>, TK, TT>
        extends ITargetPatternLike<Param, T, TK, TT>,
                ITargetType<T, TK, TT>,
                IDataType<TT> {

}
