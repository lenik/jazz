package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.pattern.template.IKeyPatternLike;

public interface IKeyPattern<Param, K>
        extends IKeyPatternLike<Param, K> {

    /**
     * @param key Must be instance of K
     * @return parameter object for captured content. null if not matched.
     */
    @Override
    Param match(Object key);
}
