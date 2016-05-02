package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.ScopedRef;

public class CharsetVars
        extends ScopedRef<Charset> {

    public CharsetVars() {
        super(Charset.class);
    }

    @Override
    public Charset getDefaultValue() {
        return Charset.defaultCharset();
    }

    /**
     * @throws NullPointerException
     *             If <code>scope</code> is <code>null</code>.
     * @see #get(IScopeInstance)
     */
    public String getCharsetName(IScopeInstance scope) {
        Charset charset = get(scope);
        if (charset == null)
            throw new NullPointerException("charset");
        return charset.name();
    }

    /**
     * @throws NullPointerException
     *             If <code>scope</code> or <code>charsetName</code> is <code>null</code>.
     * @throws IllegalCharsetNameException
     *             If the given charset name is illegal
     * @throws UnsupportedCharsetException
     *             - If no support for the named charset is available in this instance of the Java
     *             virtual machine
     * @see #set(IScopeInstance, Charset)
     */
    public void setCharsetName(IScopeInstance scope, String charsetName) {
        if (scope == null)
            throw new NullPointerException("scope");
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        Charset charset = Charset.forName(charsetName);
        set(scope, charset);
    }

}
