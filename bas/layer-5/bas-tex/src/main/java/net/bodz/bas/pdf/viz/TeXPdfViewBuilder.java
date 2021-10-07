package net.bodz.bas.pdf.viz;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;
import net.bodz.bas.tex.dom.TeXCompiler;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class TeXPdfViewBuilder<T>
        extends AbstractPdfViewBuilder<T> {

    static final Logger logger = LoggerFactory.getLogger(TeXPdfViewBuilder.class);

    WorkDirManager workDirManager = new WorkDirManager();

    public TeXPdfViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public void buildPdfView(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        IVariantMap<String> q = VariantMaps.fromRequest(ctx.getRequest());

        BCharOut texBuf = new BCharOut();
        buildTeX(ctx, texBuf, ref);
        String tex = texBuf.toString();

        Integer reuse = q.getInt("reuse");
        File workDir = workDirManager.getWorkDir(reuse);

        TeXCompiler compiler = new TeXCompiler("tex2pdf", workDir, reuse == null);
        try {
            byte[] pdfData = compiler.compile(tex);
            ServletOutputStream out = resp.getOutputStream();
            out.write(pdfData);
            out.flush();
        } finally {
            compiler.clean();
        }
    }

    public abstract void buildTeX(IHttpViewContext ctx, IPrintOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

}
