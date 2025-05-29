package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.ITargetType;
import net.bodz.bas.make.pattern.template.IKeyPatternLike;

public interface ITargetPattern<Param, T extends IKeyData<TK, TT>, TK, TT>
        extends IKeyPatternLike<Param, TK>,
                ITargetType<T, TK, TT> {

    /**
     * @param target Must be instance of T
     * @return parameter object for captured content. null if not matched.
     */
    @Override
    Param match(Object target);

}
