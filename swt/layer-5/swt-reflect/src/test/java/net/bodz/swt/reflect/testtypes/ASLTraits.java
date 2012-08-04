package net.bodz.swt.reflect.testtypes;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;

public class ASLTraits
        extends AbstractCommonTraits<ASL> {

    public ASLTraits() {
        super(ASL.class);
    }

    @Override
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
            return this;
        default:
            return null;
        }
    }

    @Override
    public ASL parse(String text)
            throws ParseException {
        int slash = text.indexOf('/');
        int age = Integer.parseInt(text.substring(0, slash));
        text = text.substring(slash + 1);
        slash = text.indexOf('/');
        String sex = text.substring(0, slash);
        String location = text.substring(slash + 1);
        return new ASL(age, "m".equalsIgnoreCase(sex), location);
    }

}