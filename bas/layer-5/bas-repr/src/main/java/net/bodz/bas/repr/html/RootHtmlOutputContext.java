package net.bodz.bas.repr.html;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.impl.EncodedCharOut;
import net.bodz.bas.io.impl.PrintOutImpl;

public class RootHtmlOutputContext
        implements IHtmlOutputContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private OutputStream outputStream;
    private IByteOut byteOut;
    private IPrintOut printOut;

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
        printOut = PrintOutImpl.from(charOut);
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
    public IPrintOut getPrintOut() {
        return printOut;
    }

}
