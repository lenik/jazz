package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.context.IContext;

public class CharsetCtl
        extends ContextLocal<Charset> {

    public CharsetCtl() {
        super(Charset.class);
    }

    @Override
    public Charset getDefaultValue() {
        return Charset.defaultCharset();
    }

    /**
     * @throws NullPointerException
     *             If <code>context</code> is <code>null</code>.
     * @see #get(IContext)
     */
    public String getCharsetName(IContext context) {
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
     * @see #set(IContext, Charset)
     */
    public void setCharsetName(IContext context, String charsetName) {
        if (context == null)
            throw new NullPointerException("context");
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        Charset charset = Charset.forName(charsetName);
        set(context, charset);
    }

}
