package net.bodz.bas.c.java.lang;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.Parameter;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;

public class DoubleTraits
        extends AbstractCommonTraits<Double> {

    /**
     * The distribution algorithm used to generate samples.
     * 
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ValueType(String.class)
    public static final String sampleDistribution = "sample.distribution";
    public static final String normalSampleDistribution = "normal";
    public static final String gaussianSampleDistribution = "gaussian";
    public static final String defaultSampleDistribution = normalSampleDistribution;

    public DoubleTraits() {
        super(Double.class);
    }

    @Override
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
        case ISampleGenerator.traitIndex:
            return this;
        }
        return null;
    }

    @Override
    public Double parse(String text)
            throws ParseException {
        try {
            return Double.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Double newSample()
            throws CreateException {
        return random.nextDouble();
    }

    @Override
    public Double newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException {
        String distribution = defaultSampleDistribution;
        Parameter distributionParam = null;

        for (Parameter param : negotiation) {
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
            return random.nextDouble();
        else if (gaussianSampleDistribution.equals(distribution))
            return random.nextGaussian();
        else
            throw new NegotiationException(distributionParam);
    }

}
