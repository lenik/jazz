package net.bodz.bas.make.type;

import net.bodz.bas.make.pattern.template.IDataPatternLike;

public interface IDataExtendsPattern<Param, T>
        extends IDataPatternLike<Param, T> {

    Class<?>[] getInterfaces();

    /**
     * @return parameter object for captured content. null if not matched.
     */
    @Override
    Param match(Object instance);

}
