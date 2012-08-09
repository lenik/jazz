package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.meta.optim.Stateless;

@Stateless
public class Binary_String
        extends BinaryProcessUnit {

    private Charset charset;

    public Binary_String(String charsetName) {
        this(Charset.forName(charsetName));
    }

    public Binary_String(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void recv(byte[] bytes, int start, int end)
            throws IOException {
        String string = new String(bytes, start, end - start, charset);
        send(string);
    }

}
