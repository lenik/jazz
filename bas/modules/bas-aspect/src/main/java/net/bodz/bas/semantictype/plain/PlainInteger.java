package net.bodz.bas.semantictype.plain;

import java.util.Random;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;

public class PlainInteger
        extends PlainType<Integer> {

    public PlainInteger() {
        super(Integer.class, "INTEGER");
    }

    @Override
    public Object parse(String text)
            throws ParseException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    private static Random random = new Random();

    @Override
    public Integer newSample()
            throws CreateException {
        return random.nextInt();
    }

    static final PlainInteger instance = new PlainInteger();

    public static PlainInteger getInstance() {
        return instance;
    }

}
