package net.bodz.pkg.obfuz.sysid;

import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.SystemException;

public abstract class AbstractSysIdProvider
        implements ISysIdProvider {

    private final String separator;

    public AbstractSysIdProvider(String separator) {
        if (separator == null)
            separator = "";
        this.separator = separator;
    }

    @Override
    public String getIdString()
            throws SystemException {
        byte[] id = getId();
        return formatId(id);
    }

    @Override
    public String formatId(byte[] id) {
        if (id == null)
            return null;
        HexCodec hex = new HexCodec(separator);
        String s = hex.encode(id);
        return s;
    }

    @Override
    public byte[] parseId(String idString)
            throws ParseException {
        HexCodec hex = new HexCodec(separator);
        byte[] id = hex.decode(idString);
        return id;
    }

}
