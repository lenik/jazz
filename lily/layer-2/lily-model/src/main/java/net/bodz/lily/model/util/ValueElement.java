package net.bodz.lily.model.util;

import org.json.JSONObject;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.MutableElement;

public class ValueElement
        extends MutableElement
        implements IJsonSerializable {

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
    public void readObject(JSONObject json)
            throws ParseException {
        switch (jsonFormat) {
        case FLOT:
            for (Object key : json.keySet()) {
                String name = (String) key;
                String str = (String) json.get(name);
                switch (name) {
                case "label":
                    setLabel(iString.fn.val(str));
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
    public void writeObject(IJsonOut out) {
        switch (jsonFormat) {
        case FLOT:
            out.object();
            {
                out.entry("label", getLabel());
                out.entry("data", value);
            }
            out.endObject();
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
