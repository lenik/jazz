package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;

public class Css3Int
        implements ICss3Int, Serializable {

    private static final long serialVersionUID = 1L;

    public int type;
    public int value;

    public Css3Int(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public Css3Int(int value) {
        this.type = NUMBER;
        this.value = value;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.type = NUMBER;
        this.value = value;
    }

    public static Css3Int parse(String str)
            throws ParseException {
        Css3Int result = parse(str);
        if (result == null)
            throw new ParseException("Illegal integer: " + str);
        return result;
    }

    public static Css3Int parse(String str, Css3Int fail) {
        if (str == null)
            throw new NullPointerException("str");

        if (StringPred.isDecimal(str)) {
            int num = Integer.parseInt(str);
            return new Css3Int(num);
        }

        switch (str) {
        case "auto":
            return new Css3Int(AUTO, 0);
        case "none":
            return new Css3Int(NONE, 0);
        default:
            return fail;
        }
    }

}
