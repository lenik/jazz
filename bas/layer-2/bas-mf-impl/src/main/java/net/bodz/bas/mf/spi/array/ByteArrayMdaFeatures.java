package net.bodz.bas.mf.spi.array;

import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.rtx.INegotiation.IParameter;

public class ByteArrayMdaFeatures
        extends AbstractArrayMdaFeatures<byte[]> {

    /**
     * The charset used to encode the text in parsing.
     * <p>
     * Set to <code>null</code> to use the default charset.
     */
    @ParameterType(Charset.class)
    public static final String textformCharset = "textform.charset";
    public static final Charset defaultTextformCharset = Charset.defaultCharset();

    public ByteArrayMdaFeatures() {
        super(byte[].class);
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        if (mdaFeatureIndex == IParser.mdaFeatureIndex)
            return this;
        return null;
    }

    @Override
    public byte[] parse(String text)
            throws ParseException {
        return text.getBytes(defaultTextformCharset);
    }

    @Override
    public byte[] parse(String text, INegotiation negotiation)
            throws ParseException {
        Charset charset = defaultTextformCharset;
        for (IParameter param : negotiation) {
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
    public byte[] newSample(ArraySampleParameters sampleParameters) {
        int length = sampleParameters.nextLength();
        byte[] sample = new byte[length];
        for (int i = 0; i < length; i++)
            sample[i] = (byte) random.nextInt();
        return sample;
    }

}
