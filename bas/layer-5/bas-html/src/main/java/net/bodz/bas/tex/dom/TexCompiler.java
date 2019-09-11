package net.bodz.bas.tex.dom;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class TexCompiler {

    static final Logger logger = LoggerFactory.getLogger(TexCompiler.class);

    String jobname;
    File workdir;
    boolean autoClean = true;

    public TexCompiler(String jobname) {
        if (jobname == null)
            throw new NullPointerException("jobname");
        this.jobname = jobname;

        try {
            workdir = TempFile.createTempDirectory();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create tmp dir.", e);
        }
    }

    public boolean isAutoClean() {
        return autoClean;
    }

    public void setAutoClean(boolean autoClean) {
        this.autoClean = autoClean;
    }

    public byte[] compile(String src)
            throws IOException {
        File texFile = new File(workdir, jobname + ".tex");
        new FileResource(texFile).to(StreamWriting.class).writeString(src);

        File pdfFile = new File(workdir, jobname + ".pdf");
        pdfFile.delete();

        String[] cmdv = { "cooltex", "-abj", jobname, texFile.getPath() };
        try {
            Process process = Processes.exec(cmdv);
            Processes.iocap(process, System.out);
        } catch (Exception e) {
            logger.error(e, "Exec failed.");
            return null;
        }

        if (!pdfFile.exists())
            return null;

        byte[] pdf = new FileResource(pdfFile).to(StreamReading.class).read();

        if (autoClean) {
            DirectoryStream<Path> ds = Files.newDirectoryStream(workdir.toPath(), jobname + ".*");
            try {
                for (Path f : ds)
                    f.toFile().delete();
            } finally {
                ds.close();
            }
        }

        return pdf;
    }

    public void clean() {
        FileTree.delete(workdir, TreeDeleteOption.DELETE_TREE);
    }

    @Override
    protected void finalize()
            throws Throwable {
        super.finalize();
        clean();
    }

}
