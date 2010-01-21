package net.bodz.bas.io.resource.builtin;

import java.io.IOException;

import net.bodz.bas.io.resource.AbstractStreamInputSource;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.StringCharIn;

public class StringSource
        extends AbstractStreamInputSource {

    private final String string;

    /**
     * @throws NullPointerException
     *             If <code>string</code> is <code>null</code>.
     */
    public StringSource(String string) {
        if (string == null)
            throw new NullPointerException("string");
        this.string = string;
    }

    @Override
    public ICharIn newCharIn()
            throws IOException {
        return new StringCharIn(string);
    }

}
