package net.bodz.bas.c.java.lang;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.mf.std.ISampleGenerator;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.rtx.INegotiation.IParameter;
import net.bodz.bas.rtx.NegotiationException;

public class FloatMdaFeatures
        extends AbstractCommonMdaFeatures<Float> {

    /**
     * The distribution algorithm used to generate samples.
     * 
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ParameterType(String.class)
    public static final String sampleDistribution = DoubleMdaFeatures.sampleDistribution;
    public static final String normalSampleDistribution = DoubleMdaFeatures.normalSampleDistribution;
    public static final String gaussianSampleDistribution = DoubleMdaFeatures.gaussianSampleDistribution;
    public static final String defaultSampleDistribution = DoubleMdaFeatures.defaultSampleDistribution;

    public FloatMdaFeatures() {
        super(Float.class);
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
            throws CreateException {
        String distribution = defaultSampleDistribution;
        IParameter distributionParam = null;

        for (IParameter param : negotiation) {
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
