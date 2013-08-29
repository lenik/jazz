package net.bodz.bas.ant.logger;

import net.bodz.bas.ant.util.ValueConstruct;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.log.Logger;

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
