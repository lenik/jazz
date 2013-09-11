package net.bodz.bas.typer.spi.array;

import java.util.Random;

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
    public char[] parse(String text, IOptions options)
            throws ParseException {
        return text.toCharArray();
    }

    @Override
    public char[] newSample(IOptions options)
            throws CreateException {
        ISampleGenerator<Character> charSample = options.get(sampleCharSample, defaultSampleCharSample);
        Random prng = options.get(Random.class, random);
        int minLen = options.getInt(sampleMinLength, defaultSampleMinLength);
        int maxLen = options.getInt(sampleMaxLength, defaultSampleMaxLength);
        int length = minLen + prng.nextInt(maxLen - minLen);

        char[] sample = new char[length];
        for (int i = 0; i < length; i++)
            sample[i] = charSample.newSample();
        return sample;
    }

}
