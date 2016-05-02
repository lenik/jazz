package net.bodz.bas.html.viz;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.html.artifact.ArtifactType;
import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.dom.IHtmlHeadData;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.xml.dom.XmlCopy;

public class HeadFn {

    public static void writeHeadData(IHtmlViewContext ctx, IHtmlOut head)
            throws IOException {
        writeMetas(ctx, head);
        writeImports(ctx, head);

        IHtmlHeadData headData = ctx.getHeadData();
        XmlCopy.copy(headData.getOptHead(), head, true);
    }

    public static void writeMetas(IHtmlViewContext ctx, IHtmlOut head)
            throws IOException {
        IHtmlHeadData headData = ctx.getHeadData();

        String title = headData.getTitle().trim();
        if (title != null)
            head.title().text(title);

        head.meta().httpEquiv(IHtmlHeadData.HTTP_CONTENT_TYPE).content(ctx.getResponse().getContentType());
        head.meta().name(IHtmlHeadData.META_GENERATOR).content("Jazz BAS Repr/HTML 2.0");

        for (Entry<String, String> entry : headData.getHttpEquivMetaMap().entrySet())
            head.meta().httpEquiv(entry.getKey()).content(entry.getValue());

        for (Entry<String, String> entry : headData.getMetaMap().entrySet())
            head.meta().name(entry.getKey()).content(entry.getValue());
    }

    public static void writeImports(IHtmlViewContext ctx, IHtmlOut head)
            throws IOException {
        IArtifactManager artifactManager = ctx.query(IArtifactManager.class);
        IHtmlHeadData headData = ctx.getHeadData();

        for (IArtifact artifact : artifactManager.getClosure(headData, ArtifactType.STYLESHEET, null)) {
            head.link().css(artifact.getAnchor().toString());
        }

        for (IArtifact artifact : artifactManager.getClosure(headData, ArtifactType.SCRIPT, null)) {
            head.script().javascriptSrc(artifact.getAnchor().toString());
        }
    }

}
