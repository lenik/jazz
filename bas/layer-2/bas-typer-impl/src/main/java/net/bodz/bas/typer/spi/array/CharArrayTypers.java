package net.bodz.bas.typer.spi.array;

import net.bodz.bas.c.java.lang.CharacterTypers;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class CharArrayTypers
        extends AbstractArrayTypers<char[]> {

    /**
     * The character sample generator.
     */
    @ParameterType(ISampleGenerator.class)
    public static final String sampleCharSample = "sample.charSample";
    public static final ISampleGenerator<Character> defaultSampleCharSample = new CharacterTypers();

    public CharArrayTypers() {
        super(char[].class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        if (typerIndex == IParser.typerIndex)
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
        public void negotiate(IOptions options) {
            super.negotiate(options);
            charSample = options.get(sampleCharSample, charSample);
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