package net.bodz.bas.site.org;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class SiteGraph_svg
        extends AbstractHttpViewBuilder<SiteGraphNode> {

    public SiteGraph_svg() {
        super(SiteGraphNode.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, SiteGraphNode value) {
        return ContentTypes.image_svg_xml;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<SiteGraphNode> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = ctx.getRequest();

        BCharOut dotBuf = new BCharOut();
        SiteGraphDotBuilder dotBuilder = new SiteGraphDotBuilder(TreeOutImpl.from(dotBuf));

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = req.getParameter(name);
            dotBuilder.put(name, value);
        }

        dotBuilder.buildGraph(ref.get());

        File tempDotFile = File.createTempFile("site", ".dot");
        FileResource tempDot = new FileResource(tempDotFile);
        tempDot.setCharset(Charsets.UTF_8);
        tempDot.to(StreamWriting.class).writeString(dotBuf.toString());

        InputStream svgIn;
        try {
            Process proc = Processes.exec("/usr/bin/dot", "-Tsvg", tempDotFile.getPath());
            svgIn = proc.getInputStream();

            ByteArrayOutputStream buf = new ByteArrayOutputStream(10000);
            byte[] block = new byte[4096];
            int cb;
            while ((cb = svgIn.read(block)) != -1)
                buf.write(block, 0, cb);
            svgIn.close();

            try {
                ServletOutputStream out = resp.getOutputStream();
                out.write(buf.toByteArray());
            } catch (IllegalStateException ise) {
                PrintWriter out = resp.getWriter();
                String text = new String(buf.toByteArray());
                out.write(text);
            }
        } catch (Exception e) {
            throw new ViewBuilderException(e.getMessage(), e);
        } finally {
            tempDotFile.delete();
        }

        return null;
    }

}
