package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.SystemException;
import net.bodz.bas.text.encodings.HexEncoding;

public abstract class _SysIdProvider implements SysIdProvider {

    private final String separator;

    public _SysIdProvider(String separator) {
        if (separator == null)
            separator = "";
        this.separator = separator;
    }

    @Override
    public String getIdString() throws SystemException {
        byte[] id = getId();
        return formatId(id);
    }

    @Override
    public String formatId(byte[] id) {
        if (id == null)
            return null;
        HexEncoding hex = new HexEncoding(separator);
        String s = hex.encode(id);
        return s;
    }

    @Override
    public byte[] parseId(String idString) throws ParseException {
        HexEncoding hex = new HexEncoding(separator);
        byte[] id = hex.decode(idString);
        return id;
    }

}
