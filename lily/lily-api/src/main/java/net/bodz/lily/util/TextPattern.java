package net.bodz.lily.util;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class TextPattern
        implements
            Serializable,
            IJsonForm,
            IXmlForm {

    private static final long serialVersionUID = 1L;

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
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
