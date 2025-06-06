package net.bodz.bas.make.type;

import net.bodz.bas.make.pattern.template.IKeyPatternLike;

public interface IKeyExtendsPattern<Param, K>
        extends IKeyPatternLike<Param, K> {

    Class<?>[] getInterfaces();

    /**
     * @return parameter object for captured content. null if not matched.
     */
    @Override
    Param match(Object instance);

}
