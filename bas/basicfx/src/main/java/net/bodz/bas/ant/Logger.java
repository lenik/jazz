package net.bodz.bas.ant;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.util.LogTerm;

public class Logger extends ValueConstruct {

    @Override
    public LogTerm create(Class<?>[] prependTypes, Object[] prependValues) throws CreateException {
        Object obj = super.create(prependTypes, prependValues);
        if (!(obj instanceof LogTerm))
            throw new CreateException(AppNLS.getString("Logger.notLogTerm") + obj); //$NON-NLS-1$
        return (LogTerm) obj;
    }

}
