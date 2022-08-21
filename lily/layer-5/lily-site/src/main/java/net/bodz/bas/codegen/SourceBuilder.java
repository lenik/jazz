package net.bodz.bas.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public abstract class SourceBuilder<model_t> {

    static final Logger logger = LoggerFactory.getLogger(SourceBuilder.class);

    protected abstract IFileInfo getFileInfo(model_t model);

    protected Boolean shouldOverwrite(model_t model) {
        return null;
    }

    public final boolean buildFile(model_t model)
            throws IOException {
        return buildFile(model, false);
    }

    public final boolean buildFile(model_t model, boolean defaultOverwrite)
            throws IOException {
        IFileInfo fileInfo = getFileInfo(model);
        File file = fileInfo.toFile();
        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();

        if (file.exists()) {
            Boolean shouldOverwrite = shouldOverwrite(model);
            boolean overwrite = shouldOverwrite != null ? shouldOverwrite.booleanValue() : defaultOverwrite;
            if (overwrite)
                logger.info("overwrite " + file);
            else
                return false;
        }

        String href = FilePath.getRelativePath(file.getPath(), fileInfo.getBaseDir().getPath() + "/");
        logger.info("build " + href);
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            OutputStreamWriter osw = new OutputStreamWriter(fileOut, Charsets.UTF8);
            WriterPrintOut wpo = new WriterPrintOut(osw);
            ITreeOut out = TreeOutImpl.from(wpo);
            build(out, model);
            out.flush();
        }
        return true;
    }

    public abstract void build(ITreeOut out, model_t model);

}
