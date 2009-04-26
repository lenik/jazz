package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.SystemException;

public interface SysIdProvider {

    byte[] getId() throws SystemException;

    String getIdString() throws SystemException;

    String formatId(byte[] id);

    byte[] parseId(String idString) throws ParseException;

}
