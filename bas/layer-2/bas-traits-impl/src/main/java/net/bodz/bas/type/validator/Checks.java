package net.bodz.bas.type.validator;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.traits.IValidator;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.valtype.util.ClassInstance;

public class Checks {

    public static IValidator<?> getChecker(AnnotatedElement elm)
            throws CreateException {
        CheckBy checkBy = elm.getAnnotation(CheckBy.class);
        return getChecker(checkBy);
    }

    public static IValidator<?> getChecker(CheckBy checkBy)
            throws CreateException {
        if (checkBy == null)
            return null;
        Class<? extends IValidator<?>> checkerClass = checkBy.value();
        if (checkerClass == IValidator.class)
            return null;
        String param = checkBy.param();
        if (param.isEmpty())
            param = null;
        if (param == null)
            return ClassInstance.getClassInstance(checkerClass);
        else
            return ClassInstance.getClassInstance(checkerClass, param);
    }

}
