package net.bodz.bas.c.java.lang;

import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class StringTypers
        extends AbstractCommonTypers<String> {

    /**
     * The min length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String sampleMinLength = "sample.minLength";
    public static final int defaultSampleMinLength = 0;

    /**
     * The max length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String sampleMaxLength = "sample.maxLength";
    public static final int defaultSampleMaxLength = 32;

    /**
     * The character sample generator.
     */
    @ParameterType(ISampleGenerator.class)
    public static final String sampleCharSample = "sample.charSample";
    public static final ISampleGenerator<Character> defaultSampleCharSample = new CharacterTypers();

    public StringTypers() {
        super(String.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public String parse(String text, IOptions options)
            throws ParseException {
        return text;
    }

    @Override
    public String newSample(IOptions options)
            throws CreateException {
        int minLength = options.getInt(sampleMinLength, defaultSampleMinLength);
        int maxLength = options.getInt(sampleMaxLength, defaultSampleMaxLength);
        ISampleGenerator<Character> charSample = options.get(sampleCharSample, defaultSampleCharSample);

        Random prng = options.get(Random.class, random);

        int length = minLength + prng.nextInt(maxLength + 1 - minLength);
        StringBuilder sample = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sample.append(charSample.newSample());
        return sample.toString();
    }

}
