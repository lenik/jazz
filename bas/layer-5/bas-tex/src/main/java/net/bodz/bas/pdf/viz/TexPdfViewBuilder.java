package net.bodz.bas.pdf.viz;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.tex.dom.TeXCompiler;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class TexPdfViewBuilder<T>
        extends AbstractPdfViewBuilder<T> {

    public TexPdfViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public void buildPdfView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        BCharOut texBuf = new BCharOut();
        buildTex(ctx, texBuf, ref);
        String tex = texBuf.toString();

        TeXCompiler compiler = new TeXCompiler("tex2pdf");
        try {
            byte[] pdfData = compiler.compile(tex);
            ServletOutputStream out = resp.getOutputStream();
            out.write(pdfData);
            out.flush();
        } finally {
            compiler.clean();
        }
    }

    public abstract void buildTex(IHttpViewContext ctx, IPrintOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

}
