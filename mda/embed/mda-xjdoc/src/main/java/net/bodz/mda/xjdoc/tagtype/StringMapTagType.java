package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class StringMapTagType
        extends TagSpecKeyTagType {

    public StringMapTagType() {
        super(StringTagType.getInstance());
    }

    @Override
    public Map<?, ?> parseEntry(Object cont, String suffix, String string, IOptions options)
            throws ParseException {
        Map<?, ?> map = super.parseEntry(cont, suffix, string, options);
        return map;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException {
        super.writeEntries(out, prefix, value, options);
    }

    static final StringMapTagType instance = new StringMapTagType();

    public static StringMapTagType getInstance() {
        return instance;
    }

}
