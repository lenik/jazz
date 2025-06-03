package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterType;
import net.bodz.bas.make.ITargetType;

public interface ITargetPatternLike<Param, T extends IKeyData<TK, TT>, TK, TT>
        extends ITargetType<T, TK, TT>,
                IParameterType<Param> {

    /**
     * @param target Must be instance of T
     * @return parameter object for captured content. null if not matched.
     */
    Param match(IKeyData<?, ?> target);

}
