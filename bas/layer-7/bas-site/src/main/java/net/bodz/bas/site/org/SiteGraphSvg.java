package net.bodz.bas.site.org;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class SiteGraphSvg
        extends AbstractHtmlViewBuilder<SiteGraphNode> {

    public SiteGraphSvg() {
        super(SiteGraphNode.class);
    }

    @Override
    public ContentType getContentType(SiteGraphNode value) {
        return ContentTypes.image_svg_xml;
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<SiteGraphNode> ref, IOptions options)
            throws ViewBuilderException, IOException {
        BCharOut dotBuf = new BCharOut();
        SiteGraphDotBuilder dotBuilder = new SiteGraphDotBuilder(TreeOutImpl.from(dotBuf));

        HttpServletRequest request = ctx.getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            dotBuilder.put(name, value);
        }

        dotBuilder.buildGraph(ref.get());

        File tempDotFile = File.createTempFile("site", ".dot");
        FileResource tempDot = new FileResource(tempDotFile);
        tempDot.setCharset(Charsets.UTF8);
        tempDot.to(StreamWriting.class).writeString(dotBuf.toString());

        InputStream svgIn;
        try {
            IByteOut out = ctx.getByteOut();

            Process proc = Processes.exec("/usr/bin/dot", "-Tsvg", tempDotFile.getPath());
            svgIn = proc.getInputStream();

            byte[] block = new byte[4096];
            int cb;
            while ((cb = svgIn.read(block)) != -1)
                out.write(block, 0, cb);

            svgIn.close();
            out.close();
        } catch (Exception e) {
            throw new ViewBuilderException(e.getMessage(), e);
        } finally {
            tempDotFile.delete();
        }

        return null;
    }

}
