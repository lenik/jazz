package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.IKeyType;
import net.bodz.bas.make.IParameterType;

public interface IKeyPatternLike<Param, K>
        extends IKeyType<K>,
                IParameterType<Param> {

    /**
     * @param key Must be instance of K
     * @return parameter object for captured content. null if not matched.
     */
    Param match(Object key);

}
