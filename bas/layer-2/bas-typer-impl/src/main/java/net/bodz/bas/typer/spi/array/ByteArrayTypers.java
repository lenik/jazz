package net.bodz.bas.typer.spi.array;

import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IParser;

public class ByteArrayTypers
        extends AbstractArrayTypers<byte[]> {

    /**
     * The charset used to encode the text in parsing.
     * <p>
     * Set to <code>null</code> to use the default charset.
     */
    @ParameterType(Charset.class)
    public static final String textformCharset = "textform.charset";
    public static final Charset defaultTextformCharset = Charset.defaultCharset();

    public ByteArrayTypers() {
        super(byte[].class);
    }

    @Override
    protected Object _query(int typerIndex) {
        if (typerIndex == IParser.typerIndex)
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
