package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class StringMapTagType
        extends TagSpecKeyTagType {

    public StringMapTagType() {
        super(StringTagType.getInstance());
    }

    @Override
    public Map<?, ?> parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        Map<?, ?> map = super.parseEntry(cont, suffix, string, negotiation);
        return map;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        super.writeEntries(out, prefix, value, negotiation);
    }

    static final StringMapTagType instance = new StringMapTagType();

    public static StringMapTagType getInstance() {
        return instance;
    }

}
