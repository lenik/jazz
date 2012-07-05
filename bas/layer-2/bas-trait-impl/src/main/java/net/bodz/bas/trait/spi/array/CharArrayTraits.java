package net.bodz.bas.trait.spi.array;

import net.bodz.bas.c.java.lang.CharacterTraits;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.meta.util.ReferredType;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;

public class CharArrayTraits
        extends AbstractArrayTraits<char[]> {

    /**
     * The character sample generator.
     */
    @ReferredType(ISampleGenerator.class)
    public static final String sampleCharSample = "sample.charSample";
    public static final ISampleGenerator<Character> defaultSampleCharSample = new CharacterTraits();

    public CharArrayTraits() {
        super(char[].class);
    }

    @Override
    protected Object query(int traitIndex) {
        if (traitIndex == IParser.traitIndex)
            return this;
        return null;
    }

    @Override
    public char[] parse(String text)
            throws ParseException {
        return text.toCharArray();
    }

    public static class CharArraySampleParameters
            extends ArraySampleParameters {

        private ISampleGenerator<Character> charSample;

        public CharArraySampleParameters(int minLength, int maxLength, ISampleGenerator<Character> charSample) {
            super(minLength, maxLength);
            if (charSample == null)
                throw new NullPointerException("charSample");
            setCharSample(charSample);
        }

        public ISampleGenerator<Character> getCharSample() {
            return charSample;
        }

        public void setCharSample(ISampleGenerator<Character> charSample) {
            if (charSample == null)
                throw new NullPointerException("charSample");
            this.charSample = charSample;
        }

        @Override
        public void negotiate(INegotiation negotiation)
                throws NegotiationException {
            super.negotiate(negotiation);
            charSample = negotiation.get(sampleCharSample, charSample);
        }

    }

    @Override
    public CharArraySampleParameters newSampleParameters() {
        return new CharArraySampleParameters(//
                defaultSampleMinLength, //
                defaultSampleMaxLength, //
                defaultSampleCharSample);
    }

    @Override
    public char[] newSample(ArraySampleParameters asp)
            throws CreateException {
        CharArraySampleParameters casp = (CharArraySampleParameters) asp;
        ISampleGenerator<Character> charSample = casp.getCharSample();

        int length = casp.nextLength();
        char[] sample = new char[length];
        for (int i = 0; i < length; i++)
            sample[i] = charSample.newSample();
        return sample;
    }

}
