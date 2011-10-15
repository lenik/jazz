package net.bodz.bas.traits.provider.array;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;
import net.bodz.bas.type.java.lang.CharacterTraits;

public class CharArrayTraits
        extends AbstractArrayTraits<char[]> {

    /**
     * The character sample generator.
     */
    @ValueType(ISampleGenerator.class)
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
        public boolean negotiate(NegotiationParameter param)
                throws NegotiationException {
            if (param.accept(sampleCharSample))
                charSample = param.value();
            else
                return super.negotiate(param);
            return true;
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
