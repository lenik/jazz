package net.bodz.pkg.obfuz.sysid;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.SystemException;

public interface ISysIdProvider {

    byte[] getId()
            throws SystemException;

    String getIdString()
            throws SystemException;

    String formatId(byte[] id);

    byte[] parseId(String idString)
            throws ParseException;

}
