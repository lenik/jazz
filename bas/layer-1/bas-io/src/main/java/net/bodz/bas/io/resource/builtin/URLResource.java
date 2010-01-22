package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.ReaderCharIn;
import net.bodz.bas.sio.WriterCharOut;

public class URLResource
        extends AbstractStreamResource {

    private final URL url;

    public URLResource(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    public URL getURL() {
        return url;
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        return url.openStream();
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return url.openConnection().getOutputStream();
    }

    @Override
    public IByteIn newByteIn()
            throws IOException {
        return new InputStreamByteIn(newInputStream());
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        return new OutputStreamByteOut(newOutputStream());
    }

    @Override
    public ICharIn newCharIn()
            throws IOException {
        return new ReaderCharIn(newReader());
    }

    @Override
    public ICharOut newCharOut()
            throws IOException {
        return new WriterCharOut(newWriter());
    }

}
