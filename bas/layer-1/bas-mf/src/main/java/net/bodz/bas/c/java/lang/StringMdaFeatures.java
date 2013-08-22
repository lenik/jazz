package net.bodz.bas.c.java.lang;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.mf.std.ISampleGenerator;
import net.bodz.bas.rtx.IOptions;

public class StringMdaFeatures
        extends AbstractCommonMdaFeatures<String> {

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
    public static final ISampleGenerator<Character> defaultSampleCharSample = new CharacterMdaFeatures();

    public StringMdaFeatures() {
        super(String.class);
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
        case ISampleGenerator.mdaFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public String parse(String text)
            throws ParseException {
        return text;
    }

    @Override
    public String newSample()
            throws CreateException {
        return newSample( //
                defaultSampleCharSample, //
                defaultSampleMinLength, //
                defaultSampleMaxLength);
    }

    @Override
    public String newSample(Map<String, Object> classification, IOptions options)
            throws CreateException {
        int minLength = options.getInt(sampleMinLength, defaultSampleMinLength);
        int maxLength = options.getInt(sampleMaxLength, defaultSampleMaxLength);
        ISampleGenerator<Character> charSample = options.get(sampleCharSample, defaultSampleCharSample);

        return newSample(charSample, minLength, maxLength);
    }

    public String newSample(ISampleGenerator<Character> charSample, int minLength, int maxLength)
            throws CreateException {
        int length = minLength + random.nextInt(maxLength + 1 - minLength);
        StringBuilder sample = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sample.append(charSample.newSample());
        return sample.toString();
    }

}
