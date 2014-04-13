package net.bodz.bas.repr.html;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.html.HtmlOutImpl;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.io.impl.EncodedCharOut;

public class RootHtmlOutputContext
        extends AbstractHtmlReprContext
        implements IHtmlOutputContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private OutputStream outputStream;
    private IByteOut byteOut;
    private IHtmlOut htmlOut;

    public RootHtmlOutputContext(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;

        outputStream = response.getOutputStream();
        byteOut = new OutputStreamByteOut(outputStream);

        String encoding = response.getCharacterEncoding();
        if (encoding == null)
            encoding = Charset.defaultCharset().name(); // or utf-8?

        ICharOut charOut = new EncodedCharOut(byteOut, encoding);
        htmlOut = HtmlOutImpl.from(charOut);
    }

    @Override
    public IHtmlOutputContext getRoot() {
        return this;
    }

    @Override
    public IHtmlOutputContext getParent() {
        return null;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public IByteOut getByteOut() {
        return byteOut;
    }

    @Override
    public IHtmlOut getOut() {
        return htmlOut;
    }

}
