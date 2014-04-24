package net.bodz.bas.repr.html;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.html.HtmlOutImpl;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.io.impl.DecodedByteOut;
import net.bodz.bas.io.impl.EncodedCharOut;

public class RootHtmlOutputContext
        extends AbstractHtmlReprContext
        implements IHttpReprContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private boolean charBased;
    private OutputStream outputStream;
    private IByteOut byteOut;
    private IHtmlOut htmlOut;

    /**
     * TODO xxxxxxxxxxxxxxxx ctx.pushBegin ... ctx. return new SubHtmlOutCtx(ctx)
     *
     * @see #flush()
     */
    public RootHtmlOutputContext(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request == null)
            throw new NullPointerException("request");
        if (response == null)
            throw new NullPointerException("response");
        this.request = request;
        this.response = response;

        String encoding = response.getCharacterEncoding();
        if (encoding == null)
            encoding = "utf-8";

        String contentType = response.getContentType();
        charBased = contentType.startsWith("text/");
        if (charBased) {
            PrintWriter writer = response.getWriter();
            IPrintOut printOut = new WriterPrintOut(writer);
            htmlOut = HtmlOutImpl.from(printOut);
            byteOut = new DecodedByteOut(printOut, encoding);
        } else {
            outputStream = response.getOutputStream();
            byteOut = new OutputStreamByteOut(outputStream);
            ICharOut charOut = new EncodedCharOut(byteOut, encoding);
            htmlOut = HtmlOutImpl.from(charOut);
        }
    }

    @Override
    public IHttpReprContext getRoot() {
        return this;
    }

    @Override
    public IHttpReprContext getParent() {
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

    @Override
    public final HttpSession getSession() {
        if (request == null)
            return null;
        else
            return request.getSession();
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

    @Override
    public void flush() {
        htmlOut.endAllTags();
    }

}
