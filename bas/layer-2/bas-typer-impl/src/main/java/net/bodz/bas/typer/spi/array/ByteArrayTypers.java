package net.bodz.bas.typer.spi.array;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class ByteArrayTypers
        extends AbstractArrayTypers<byte[]> {

    /**
     * The charset used to encode the text in parsing.
     * <p>
     * Set to <code>null</code> to use the default charset.
     */
    @ParameterType(Charset.class)
    public static final String textformCharset = "textform.charset";
    public static final Charset defaultTextformCharset = StandardCharsets.ISO_8859_1;

    public ByteArrayTypers() {
        super(byte[].class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        if (typerIndex == IParser.typerIndex)
            return this;
        if (typerIndex == IFormatter.typerIndex)
            return this;
        if (typerIndex == ISampleGenerator.typerIndex)
            return this;
        return null;
    }

    @Override
    public byte[] parse(String text, IOptions options)
            throws ParseException {
        Charset charset = options.get(textformCharset);
        if (charset != null)
            return text.getBytes(charset);

        char[] cv = text.toCharArray();
        int len = cv.length;
        byte[] bv = new byte[len];
        for (int i = 0; i < len; i++)
            bv[i] = (byte) cv[i];
        return bv;
    }

    @Override
    public String format(byte[] bytes, IOptions options) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            chars[i] = (char) (bytes[i] & 0xFF);
        return new String(chars);
    }

    @Override
    public byte[] newSample(IOptions options)
            throws CreateException {
        int minLength = options.getInt(OPTION_MIN_LENGTH, DEFAULT_MIN_LENGTH);
        int maxLength = options.getInt(OPTION_MAX_LENGTH, DEFAULT_MAX_LENGTH);

        Random prng = options.get(Random.class, random);

        int length = minLength + prng.nextInt(maxLength + 1 - minLength);

        byte[] sample = new byte[length];
        prng.nextBytes(sample);
        return sample;
    }

}
