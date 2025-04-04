package net.bodz.bas.io.res;

import java.nio.charset.Charset;

import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractResourceEntry<This>
        implements IMutableResourceEntry<This> {

    String path;
    Charset charset = getPreferredCharset();
    Boolean textMode;

    @NotNull
    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        if (charset == null)
            this.charset = getPreferredCharset();
        else
            this.charset = charset;
    }

    @Override
    public boolean isPathPresent() {
        return path != null;
    }

    @NotNull
    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public final boolean isTextMode() {
        if (textMode == null)
            return isTextModePreferred();
        else
            return textMode;
    }

    @Override
    public final void setTextMode(boolean mode) {
        textMode = mode;
    }

    public final void unsetTextMode() {
        textMode = null;
    }


}
