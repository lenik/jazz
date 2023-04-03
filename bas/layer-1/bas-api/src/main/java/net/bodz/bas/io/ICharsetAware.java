package net.bodz.bas.io;

import java.nio.charset.Charset;

public interface ICharsetAware {

    Charset getCharset();

    void setCharset(Charset charset);

    default Charset getCharset(String charsetName) {
        if (charsetName == null) {
            Charset defaultCharset = getCharset();
            if (defaultCharset == null)
                defaultCharset = Charset.defaultCharset();
            return defaultCharset;
        }
        Charset charset = Charset.forName(charsetName);
        if (charset == null)
            throw new IllegalArgumentException("invalid charset: " + charsetName);
        return charset;
    }

    default void setCharset(String charsetName) {
        Charset charset = null;
        if (charsetName == null) {
            charset = Charset.forName(charsetName);
            if (charset == null)
                throw new IllegalArgumentException("invalid charset: " + charsetName);
        }
        setCharset(charset);
    }

}
