package net.bodz.redist.obfuz.sysid;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.SystemException;

public interface SysIdProvider {

    byte[] getId()
            throws SystemException;

    String getIdString()
            throws SystemException;

    String formatId(byte[] id);

    byte[] parseId(String idString)
            throws ParseException;

}
