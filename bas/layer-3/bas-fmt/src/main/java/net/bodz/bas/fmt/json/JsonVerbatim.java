package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.ParseException;

public class JsonVerbatim
        implements IJsonSerializable {

    String str;

    public JsonVerbatim(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.verbatim(str);
    }

}
