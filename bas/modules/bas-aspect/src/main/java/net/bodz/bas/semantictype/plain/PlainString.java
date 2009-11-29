package net.bodz.bas.semantictype.plain;

import java.util.Random;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;

public class PlainString
        extends PlainType<String> {

    /**
     * @param maxLength
     *            if the max-length
     */
    public PlainString(int maxLength) {
        super(String.class, "STRING");
    }

    @Override
    public String parse(String text)
            throws ParseException {
        return text;
    }

    private static Random random = new Random();

    @Override
    public String newSample()
            throws CreateException {
        return null;
    }

}
