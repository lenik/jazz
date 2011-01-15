package net.bodz.bas.type.java.lang;

import java.nio.charset.Charset;
import java.util.Map;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;
import net.bodz.bas.lang.NegotiationParameter;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class ByteArrayTraits
        extends AbstractCommonTraits<byte[]> {

    /**
     * The charset used to encode the text in parsing.
     * <p>
     * Set to <code>null</code> to use the default charset.
     */
    @ValueType(Charset.class)
    public static final String textformCharset = "textform.charset";
    public static final Charset defaultTextformCharset = Charset.defaultCharset();

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

    public ByteArrayTraits() {
        super(byte[].class);
    }

    @Override
    public byte[] parse(String text)
            throws ParseException {
        return text.getBytes(defaultTextformCharset);
    }

    @Override
    public byte[] parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        Charset charset = defaultTextformCharset;
        for (NegotiationParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (textformCharset.equals(paramId)) {
                if (paramValue != null)
                    charset = (Charset) paramValue;
            }
        }
        return text.getBytes(charset);
    }

    @Override
    public byte[] newSample()
            throws CreateException {
        return newSample(defaultSampleMinLength, defaultSampleMaxLength);
    }

    @Override
    public byte[] newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException {
        int minLength = defaultSampleMinLength;
        int maxLength = defaultSampleMaxLength;

        for (NegotiationParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (sampleMinLength.equals(paramId))
                minLength = (Integer) paramValue;
            else if (sampleMaxLength.equals(paramId))
                maxLength = (Integer) paramValue;
        }

        return newSample(minLength, maxLength);
    }

    public byte[] newSample(int minLength, int maxLength) {
        int length = minLength + random.nextInt(maxLength + 1 - minLength);
        byte[] sample = new byte[length];
        for (int i = 0; i < length; i++)
            sample[i] = (byte) random.nextInt();
        return sample;
    }

}
