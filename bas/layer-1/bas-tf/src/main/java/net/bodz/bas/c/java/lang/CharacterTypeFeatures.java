package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.tf.std.ISampleGenerator;

public class CharacterTypeFeatures
        extends AbstractCommonTypeFeatures<Character> {

    public CharacterTypeFeatures() {
        super(Character.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        switch (typeFeatureIndex) {
        case IParser.typeFeatureIndex:
        case ISampleGenerator.typeFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public Character parse(String text)
            throws ParseException {
        if (text == null)
            return null;
        if (text.isEmpty())
            return '\u0000';
        return text.charAt(0);
    }

    /**
     * If there is something bad in the PRNG, the random.nextInt() may always returns control char.
     * 
     * This parameter is to avoid such bad case happens. If it's failed to generate a printable char
     * in this many times, a simple {@link #__prngFallback} char is returned.
     */
    private static int __prngSafe = 100;
    private static char __prngFallback = '?';

    @Override
    public Character newSample()
            throws CreateException {
        for (int i = 0; i < __prngSafe; i++) {
            char ch = (char) random.nextInt();
            boolean isPrintable = !Character.isISOControl(ch);
            if (isPrintable)
                return ch;
        }
        return __prngFallback;
    }

}
