package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IDataType;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.ITargetType;
import net.bodz.bas.make.pattern.template.IKeyPatternLike;

public interface IDataTypedTargetPattern<Param, T extends IKeyData<TK, TT>, TK, TT>
        extends IKeyPatternLike<Param, TK>,
                ITargetType<T, TK, TT>,
                IDataType<T> {

}
