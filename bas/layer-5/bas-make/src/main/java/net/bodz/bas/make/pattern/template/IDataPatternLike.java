package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.IDataType;
import net.bodz.bas.make.IParameterType;

public interface IDataPatternLike<Param, T>
        extends IDataType<T>,
                IParameterType<Param> {

    /**
     * @param data Must be instance of T
     * @return parameter object for captured content. null if not matched.
     */
    Param match(Object data);

}
