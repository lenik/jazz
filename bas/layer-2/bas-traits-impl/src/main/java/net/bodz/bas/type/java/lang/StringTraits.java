package net.bodz.bas.type.java.lang;

import java.util.Map;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class StringTraits
        extends AbstractCommonTraits<String> {

    /**
     * The min length of the sample char array, in Integer.
     */
    @ValueType(Integer.class)
    public static final String sampleMinLength = "sample.minLength";
    public static final int defaultSampleMinLength = 0;

    /**
     * The max length of the sample char array, in Integer.
     */
    @ValueType(Integer.class)
    public static final String sampleMaxLength = "sample.maxLength";
    public static final int defaultSampleMaxLength = 32;

    /**
     * The character sample generator.
     */
    @ValueType(ISampleGenerator.class)
    public static final String sampleCharSample = "sample.charSample";
    public static final ISampleGenerator<Character> defaultSampleCharSample = new CharacterTraits();

    public StringTraits() {
        super(String.class);
    }

    @Override
    protected Object query(int traitsIndex) {
        switch (traitsIndex) {
        case IParser.traitsIndex:
        case ISampleGenerator.traitsIndex:
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

    @SuppressWarnings("unchecked")
    @Override
    public String newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException {
        int minLength = defaultSampleMinLength;
        int maxLength = defaultSampleMaxLength;
        ISampleGenerator<Character> charSample = defaultSampleCharSample;

        for (NegotiationParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (sampleMinLength.equals(paramId))
                minLength = (Integer) paramValue;
            else if (sampleMaxLength.equals(paramId))
                maxLength = (Integer) paramValue;
            else if (sampleCharSample.equals(paramId))
                charSample = (ISampleGenerator<Character>) paramValue;
        }

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
