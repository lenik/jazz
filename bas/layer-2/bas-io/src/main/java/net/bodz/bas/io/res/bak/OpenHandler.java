package net.bodz.bas.io.res.bak;

import net.bodz.bas.io.res.IStreamInputSource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;

public class OpenHandler {

    IStreamInputSource source;
    Charset charset;
    List<IOpenResourceListener> openResourceListeners = new ArrayList<IOpenResourceListener>(1);

    public OpenHandler(IStreamInputSource source) {
        this.source = source;
        this.charset = source.getPreferredCharset();
    }

    public OpenHandler(IStreamInputSource source, String charsetName) {
        this.source = source;
        if (charsetName == null)
            this.charset = source.getPreferredCharset();
        else
            this.charset = Charset.forName(charsetName);
    }

    public OpenHandler(IStreamInputSource source, Charset charset) {
        this.source = source;
        if (charset == null)
            this.charset = source.getPreferredCharset();
        else
            this.charset = charset;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    public void setCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        this.charset = Charset.forName(charsetName);
    }

    public void addOpenResourceListener(IOpenResourceListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        openResourceListeners.add(listener);
    }

    public void removeOpenResourceListener(IOpenResourceListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        openResourceListeners.remove(listener);
    }

    protected void fireOpenResource(boolean output, OpenOption... options)
            throws IOException {
        OpenResourceEvent event = new OpenResourceEvent(this, output, options);
        for (IOpenResourceListener listener : openResourceListeners)
            listener.openResource(event);
    }

    protected void beforeOpenInput(OpenOption... options)
            throws IOException {
        fireOpenResource(false, options);
    }

    protected void beforeOpenOutput(OpenOption... options)
            throws IOException {
        fireOpenResource(true, options);
    }

    protected void afterOpenInput(AutoCloseable in)
            throws IOException {
    }

    protected void afterOpenOutput(AutoCloseable out)
            throws IOException {
    }

}
