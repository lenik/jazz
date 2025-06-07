package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IParameterType;
import net.bodz.bas.make.pattern.template.IKeyPatternLike;

public interface IKeyExtendsPattern<Param, K>
        extends IParameterType<Param>, //
                IKeyPatternLike<Param, K> //
{

    Class<?>[] getInterfaces();

    /**
     * @return parameter object for captured content. null if not matched.
     */
    @Override
    Param match(Object instance);

}
