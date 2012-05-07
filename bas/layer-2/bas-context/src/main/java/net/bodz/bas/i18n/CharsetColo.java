package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.context.IContextId;

public class CharsetColo
        extends ContextLocal<Charset> {

    @Override
    public Charset getRoot() {
        return Charset.defaultCharset();
    }

    /**
     * @throws NullPointerException
     *             If <code>context</code> is <code>null</code>.
     * @see #get(IContextId)
     */
    public String getCharsetName(IContextId context) {
        Charset charset = get(context);
        if (charset == null)
            throw new NullPointerException("charset");
        return charset.name();
    }

    /**
     * @throws NullPointerException
     *             If <code>context</code> or <code>charsetName</code> is <code>null</code>.
     * @throws IllegalCharsetNameException
     *             If the given charset name is illegal
     * @throws UnsupportedCharsetException
     *             - If no support for the named charset is available in this instance of the Java
     *             virtual machine
     * @see #set(IContextId, Charset)
     */
    public void setCharsetName(IContextId context, String charsetName) {
        if (context == null)
            throw new NullPointerException("context");
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        Charset charset = Charset.forName(charsetName);
        set(context, charset);
    }

    static final CharsetColo instance = new CharsetColo();

    public static CharsetColo getInstance() {
        return instance;
    }

}
