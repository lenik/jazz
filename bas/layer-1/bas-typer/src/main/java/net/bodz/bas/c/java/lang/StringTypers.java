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
    public static final String OPTION_MIN_LENGTH = "sample.minLength";
    public static final int DEFAULT_MIN_LENGTH = 0;

    /**
     * The max length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MAX_LENGTH = "sample.maxLength";
    public static final int DEFAULT_MAX_LENGTH = 32;

    /**
     * The character sample generator.
     */
    @ParameterType(ISampleGenerator.class)
    public static final String OPTION_CHAR_SAMPLE = "sample.charSample";
    public static final ISampleGenerator<Character> DEFAULT_CHAR_SAMPLE = new CharacterTypers();

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
        int minLength = options.getInt(OPTION_MIN_LENGTH, DEFAULT_MIN_LENGTH);
        int maxLength = options.getInt(OPTION_MAX_LENGTH, DEFAULT_MAX_LENGTH);
        ISampleGenerator<Character> charSample = options.get(OPTION_CHAR_SAMPLE, DEFAULT_CHAR_SAMPLE);

        Random prng = options.get(Random.class, random);

        int length = minLength + prng.nextInt(maxLength + 1 - minLength);
        StringBuilder sample = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sample.append(charSample.newSample());
        return sample.toString();
    }

    public static StringTypers INSTANCE = new StringTypers();

}
