package net.bodz.bas.ant;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.log.api.Logger;

public class LoggerVC
        extends ValueConstruct {

    @Override
    public Logger create(Class<?>[] prependTypes, Object[] prependValues)
            throws CreateException {
        Object obj = super.create(prependTypes, prependValues);
        if (!(obj instanceof Logger))
            throw new CreateException("Not a LogTerm: " + obj);
        return (Logger) obj;
    }

}
