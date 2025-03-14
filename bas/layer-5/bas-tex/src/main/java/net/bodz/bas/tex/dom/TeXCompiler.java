package net.bodz.bas.tex.dom;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class TeXCompiler
        implements Closeable {

    static final Logger logger = LoggerFactory.getLogger(TeXCompiler.class);

    String jobName;
    Path workDir;
    boolean autoClean;

    public TeXCompiler(String jobName, Path workdir, boolean autoClean) {
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
        Path texFile = workDir.resolve(jobName + ".tex");
        ResFn.path(texFile).to(StreamWriting.class).writeString(src);

        Path pdfFile = workDir.resolve(jobName + ".pdf");
        FileFn.delete(pdfFile);

        String[] cmdv = { "cooltex", "-abj", jobName, texFile.toString() };
        try {
            Process process = new ProcessBuilder().command(cmdv).start();
            process.waitFor();
        } catch (Exception e) {
            logger.error(e, "Exec failed.");
            return null;
        }

        if (Files.notExists(pdfFile))
            return null;

        byte[] pdf = ResFn.path(pdfFile).to(StreamReading.class).read();

        if (autoClean) {
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(workDir, jobName + ".*")) {
                for (Path path : dirStream)
                    FileFn.delete(path);
            }
            // if (workDir.list().length == 0)
            FileFn.delete(workDir); // only empty dir can be deleted.
        }

        return pdf;
    }

    public void clean() {
        FileTree.delete(workDir, TreeDeleteOption.DELETE_TREE);
    }

    @Override
    public void close()
            throws IOException {
        clean();
    }

}
