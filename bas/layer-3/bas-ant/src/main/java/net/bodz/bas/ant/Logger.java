package net.bodz.bas.ant;

import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.io.term.LogTerm;

public class Logger extends ValueConstruct {

    @Override
    public LogTerm create(Class<?>[] prependTypes, Object[] prependValues) throws CreateException {
        Object obj = super.create(prependTypes, prependValues);
        if (!(obj instanceof LogTerm))
            throw new CreateException("Not a LogTerm: " + obj); 
        return (LogTerm) obj;
    }

}
