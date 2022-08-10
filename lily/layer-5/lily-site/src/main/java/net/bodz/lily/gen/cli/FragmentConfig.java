package net.bodz.lily.gen.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class FragmentConfig {

    static final Logger logger = LoggerFactory.getLogger(FragmentConfig.class);

    File baseDir;
    File dir;
    String baseName;
    String extension;
//    String mainName;
    String fragmentName;

    File compose() {
        File file = new File(dir, baseName);
        return file;
    }

    public ITreeOut open(boolean overwrite)
            throws FileNotFoundException {
        return open(compose(), overwrite);
    }

    public ITreeOut open(String name, boolean overwrite)
            throws FileNotFoundException {
        File file = new File(compose(), name);
        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();
        return open(file, overwrite);
    }

    ITreeOut open(File file, boolean overwrite)
            throws FileNotFoundException {
        if (file.exists())
            if (!overwrite)
                return null;
        String href = FilePath.getRelativePath(file, baseDir);
        logger.info("Generate: " + href);
        FileOutputStream fileOut = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fileOut, Charsets.UTF8);
        WriterPrintOut wpo = new WriterPrintOut(osw);
        return TreeOutImpl.from(wpo);
    }

}
