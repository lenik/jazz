package net.bodz.lily.model.base;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.json.JsonObject;

public class TextPattern
        implements
            Serializable,
            IJsonForm,
            IXmlForm {

    String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setStartsWith(String pattern) {
        this.pattern = pattern + "%";
    }

    public void setEndsWith(String pattern) {
        this.pattern = "%" + pattern;
    }

    public void setContains(String pattern) {
        this.pattern = "%" + pattern + "%";
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
