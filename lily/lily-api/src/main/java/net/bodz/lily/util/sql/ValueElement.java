package net.bodz.lily.util.sql;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class ValueElement
        extends MutableElement
        implements IJsonForm {

    private static final long serialVersionUID = 1L;

    private Class<? extends Number> valueType;
    private Number value = 0;

    /**
     * For jQuery-flot charts.
     */
    public static final int FLOT = 0;

    private int jsonFormat = FLOT;

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        switch (jsonFormat) {
        case FLOT:
            for (Object key : o.keySet()) {
                String name = (String) key;
                String str = o.getString(name);
                switch (name) {
                case "label":
                    setLabel(StrFn.wrap(str));
                    break;
                case "data":
                    value = parseValue(str);
                    break;
                }
            }
            return;

        default:
            assert false;
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts) {
        switch (jsonFormat) {
        case FLOT:
            {
                out.entry("label", getLabel());
                out.entry("data", value);
            }
            return;

        default:
            assert false;
        }
    }

    public Number parseValue(String str)
            throws ParseException {
        if (str == null)
            return null;

        if (valueType == null) {
            try {
                if (str.contains(".")) {
                    return Double.valueOf(str);
                } else {
                    long l = Long.valueOf(str);
                    if (l >= Integer.MIN_VALUE && l <= Integer.MAX_VALUE)
                        return (int) l;
                    else
                        return l;
                }
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

        // if (valueType == Integer.class) ...
        throw new NotImplementedException();
    }

}
