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

public class FloatTraits
        extends AbstractCommonTraits<Float> {

    /**
     * The distribution algorithm used to generate samples.
     * 
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ValueType(String.class)
    public static final String sampleDistribution = DoubleTraits.sampleDistribution;
    public static final String normalSampleDistribution = DoubleTraits.normalSampleDistribution;
    public static final String gaussianSampleDistribution = DoubleTraits.gaussianSampleDistribution;
    public static final String defaultSampleDistribution = DoubleTraits.defaultSampleDistribution;

    public FloatTraits() {
        super(Float.class);
    }

    @Override
    public IParser<Float> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<Float> getSampleGenerator() {
        return this;
    }

    @Override
    public Float parse(String text)
            throws ParseException {
        try {
            return Float.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Float newSample()
            throws CreateException {
        return random.nextFloat();
    }

    @Override
    public Float newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException {
        String distribution = defaultSampleDistribution;
        NegotiationParameter distributionParam = null;

        for (NegotiationParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (sampleDistribution.equals(paramId)) {
                distribution = (String) paramValue;
                distributionParam = param;
            }
        }

        if (normalSampleDistribution.equals(distribution))
            return random.nextFloat();
        else if (gaussianSampleDistribution.equals(distribution))
            return (float) random.nextGaussian();
        else
            throw new NegotiationException(distributionParam);
    }

}
