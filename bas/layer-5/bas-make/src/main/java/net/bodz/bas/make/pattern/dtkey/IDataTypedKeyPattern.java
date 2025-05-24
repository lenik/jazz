package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IDataType;
import net.bodz.bas.make.pattern.template.IKeyPatternLike;

public interface IDataTypedKeyPattern<Param, K, T>
        extends IKeyPatternLike<Param, K>,
                IDataType<T> {

}
