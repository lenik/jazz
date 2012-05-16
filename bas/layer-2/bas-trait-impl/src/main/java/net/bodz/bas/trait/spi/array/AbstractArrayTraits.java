package net.bodz.bas.trait.spi.array;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.lang.negotiation.AbstractNegotiable;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.Parameter;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;

public abstract class AbstractArrayTraits<T>
        extends AbstractCommonTraits<T> {

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

    public AbstractArrayTraits(Class<T> arrayType) {
        super(arrayType);
    }

    public static class ArraySampleParameters
            extends AbstractNegotiable {

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
        public boolean negotiate(Parameter param)
                throws NegotiationException {
            Object paramValue = param.getValue();
            if (paramValue == null)
                return false;
            if (param.is(sampleMinLength))
                this.minLength = (Integer) paramValue;
            else if (param.is(sampleMaxLength))
                this.maxLength = (Integer) paramValue;
            else
                return false;
            return true;
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
            throws CreateException, NegotiationException {
        ArraySampleParameters sampleParameters = newSampleParameters();
        sampleParameters.negotiate(negotiation);
        return newSample(sampleParameters);
    }

    public abstract T newSample(ArraySampleParameters asp)
            throws CreateException;

}
