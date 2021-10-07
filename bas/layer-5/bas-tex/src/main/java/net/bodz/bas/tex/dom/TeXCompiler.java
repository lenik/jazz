package net.bodz.bas.tex.dom;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class TeXCompiler {

    static final Logger logger = LoggerFactory.getLogger(TeXCompiler.class);

    String jobName;
    File workDir;
    boolean autoClean;

    public TeXCompiler(String jobName, File workdir, boolean autoClean) {
        if (jobName == null)
            throw new NullPointerException("jobName");
        this.jobName = jobName;
        this.workDir = workdir;
        this.autoClean = autoClean;
    }

    public boolean isAutoClean() {
        return autoClean;
    }

    public void setAutoClean(boolean autoClean) {
        this.autoClean = autoClean;
    }

    public byte[] compile(String src)
            throws IOException {
        File texFile = new File(workDir, jobName + ".tex");
        new FileResource(texFile).to(StreamWriting.class).writeString(src);

        File pdfFile = new File(workDir, jobName + ".pdf");
        pdfFile.delete();

        String[] cmdv = { "cooltex", "-abj", jobName, texFile.getPath() };
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
            DirectoryStream<Path> dirStream = Files.newDirectoryStream(workDir.toPath(), jobName + ".*");
            try {
                for (Path path : dirStream)
                    path.toFile().delete();
            } finally {
                dirStream.close();
            }
            // if (workDir.list().length == 0)
            workDir.delete(); // only empty dir can be deleted.
        }

        return pdf;
    }

    public void clean() {
        FileTree.delete(workDir, TreeDeleteOption.DELETE_TREE);
    }

    @Override
    protected void finalize()
            throws Throwable {
        super.finalize();
        clean();
    }

}
