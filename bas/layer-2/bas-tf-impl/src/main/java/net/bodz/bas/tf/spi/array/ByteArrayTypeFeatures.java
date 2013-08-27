package net.bodz.bas.tf.spi.array;

import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.rtx.IOptions;

public class ByteArrayTypeFeatures
        extends AbstractArrayTypeFeatures<byte[]> {

    /**
     * The charset used to encode the text in parsing.
     * <p>
     * Set to <code>null</code> to use the default charset.
     */
    @ParameterType(Charset.class)
    public static final String textformCharset = "textform.charset";
    public static final Charset defaultTextformCharset = Charset.defaultCharset();

    public ByteArrayTypeFeatures() {
        super(byte[].class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        if (typeFeatureIndex == IParser.typeFeatureIndex)
            return this;
        return null;
    }

    @Override
    public byte[] parse(String text)
            throws ParseException {
        return text.getBytes(defaultTextformCharset);
    }

    @Override
    public byte[] parse(String text, IOptions options)
            throws ParseException {
        Charset charset = options.get(textformCharset, defaultTextformCharset);
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
