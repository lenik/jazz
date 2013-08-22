package net.bodz.bas.mf.spi.array;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.rtx.INegotiable;
import net.bodz.bas.rtx.INegotiation;

public abstract class AbstractArrayMdaFeatures<T>
        extends AbstractCommonMdaFeatures<T> {

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

    public AbstractArrayMdaFeatures(Class<T> arrayType) {
        super(arrayType);
    }

    public static class ArraySampleParameters
            implements INegotiable {

        private int minLength;
        private int maxLength;

        public ArraySampleParameters(int minLength, int maxLength) {
            this.minLength = minLength;
            this.maxLength = maxLength;
        }

        public int getMinLength() {
            return minLength;
        }

        public void setMinLength(int minLength) {
            this.minLength = minLength;
        }

        public int getMaxLength() {
            return maxLength;
        }

        public void setMaxLength(int maxLength) {
            this.maxLength = maxLength;
        }

        public int nextLength() {
            return minLength + random.nextInt(maxLength + 1 - minLength);
        }

        @Override
        public void negotiate(INegotiation negotiation) {
            minLength = negotiation.get(sampleMinLength, minLength);
            maxLength = negotiation.get(sampleMaxLength, maxLength);
        }

    }

    public ArraySampleParameters newSampleParameters() {
        return new ArraySampleParameters(defaultSampleMinLength, defaultSampleMaxLength);
    }

    @Override
    public T newSample()
            throws CreateException {
        return newSample(newSampleParameters());
    }

    @Override
    public T newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException {
        ArraySampleParameters sampleParameters = newSampleParameters();
        sampleParameters.negotiate(negotiation);
        return newSample(sampleParameters);
    }

    public abstract T newSample(ArraySampleParameters asp)
            throws CreateException;

}
