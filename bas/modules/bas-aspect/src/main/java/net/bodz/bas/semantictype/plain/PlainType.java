package net.bodz.bas.semantictype.plain;

import net.bodz.bas.aspect.typeinfo.ValidateException;
import net.bodz.bas.semantictype.AbstractSemanticType;

public abstract class PlainType<T>
        extends AbstractSemanticType<T> {

    public PlainType(Class<? extends T> instanceClass, String syntax) {
        super(instanceClass.getName(), null, syntax, instanceClass, null);
    }

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }

    @Override
    public void validate(Object object)
            throws ValidateException {
    }

}
