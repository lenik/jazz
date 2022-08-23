package net.bodz.bas.codegen;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

import com.github.difflib.patch.PatchFailedException;

public abstract class SourceBuilder<model_t> {

    static final Logger logger = LoggerFactory.getLogger(SourceBuilder.class);

    protected abstract IFileInfo getFileInfo(model_t model);

    protected UpdateMethod getPreferredUpdateMethod(model_t model) {
        return null;
    }

    public final boolean buildFile(model_t model)
            throws IOException {
        return buildFile(model, null);
    }

    public final boolean buildFile(model_t model, UpdateMethod fileUpdateMethod)
            throws IOException {
        UpdateMethod updateMethod = fileUpdateMethod;
        if (updateMethod == null) {
            updateMethod = getPreferredUpdateMethod(model);
            if (updateMethod == null)
                updateMethod = UpdateMethod.DIFF_MERGE;
        }

        IFileInfo fileInfo = getFileInfo(model);
        File file = fileInfo.toFile();
        String displayPath = FilePath.getRelativePath(file.getPath(), fileInfo.getBaseDir().getPath() + "/");

        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();

        FileDiffResult result = null;
        boolean saveOrig = updateMethod == UpdateMethod.DIFF_MERGE;
        if (file.exists()) {
            switch (updateMethod) {
            case NO_UPDATE:
                return false;

            case OVERWRITE:
                logger.info("    overwrite " + displayPath);
                break;

            case DIFF_MERGE:
                File origCopy = getOrigCopy(fileInfo);
                if (!origCopy.exists()) {
                    // logger.warn("can't do diff/merge: not able to read " + origCopy);
                    updateMethod = UpdateMethod.OVERWRITE;
                } else {
                    result = FileDiff.diff(origCopy, file);
                }
                break;
            }
        }

        logger.info("Build " + displayPath);

        BCharOut buf = new BCharOut(4096);
        ITreeOut out = TreeOutImpl.from(buf);
        build(out, model);
        out.flush();
        String _0b = buf.toString();
        List<String> v0b = new StringSource(_0b).read().readLines();
        List<String> v1b = v0b;

        if (result != null && result.isDifferent()) {
            logger.debug("    patch on " + displayPath);
            try {
                v1b = result.patch.applyTo(v0b);
            } catch (PatchFailedException e) {
                logger.error("Patch failed: " + e.getMessage(), e);
                return false;
            }
        }

        if (saveOrig) {
            File origCopy = getOrigCopy(fileInfo);
            if (FileDiff.diff(v0b, origCopy).isDifferent()) {
                logger.debug("    save original backup at " + origCopy);

                File origDir = origCopy.getParentFile();
                origDir.mkdirs();

                new FileResource(origCopy).write().writeString(v0b);
            }
        }

        if (FileDiff.diff(v1b, file).isSame())
            return false;

        logger.info("    save " + displayPath);
        new FileResource(file).write().writeString(v1b);
        return true;
    }

    static File getOrigCopy(IFileInfo file) {
        File baseDir = file.getBaseDir();
        File origBaseDir = new File(baseDir.getPath() + ".0");
        String localPath = file.getLocalPath();
        File origCopy = new File(origBaseDir, localPath);
        return origCopy;
    }

    public abstract void build(ITreeOut out, model_t model);

}
