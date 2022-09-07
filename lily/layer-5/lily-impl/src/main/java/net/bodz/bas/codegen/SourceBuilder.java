package net.bodz.bas.codegen;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.compare.dmp.Patch;
import net.bodz.bas.compare.dmp.PatchApplyResult;
import net.bodz.bas.compare.dmp.PatchApplyStatus;
import net.bodz.bas.compare.dmp.PatchList;
import net.bodz.bas.compare.dmp.RowEdit;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.text.LinesText;
import net.bodz.bas.text.row.IRow;
import net.bodz.bas.text.row.StringRow;

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

        PatchList<String> patchList = null;
        boolean saveOrig = updateMethod == UpdateMethod.DIFF_MERGE;
        if (file.exists()) {
            switch (updateMethod) {
            case NO_UPDATE:
                return false;

            case OVERWRITE:
                logger.debug("    overwrite " + displayPath);
                break;

            case DIFF_MERGE:
                File origCopy = getOrigCopy(fileInfo);
                if (!origCopy.exists()) {
                    // logger.warn("can't do diff/merge: not able to read " + origCopy);
                    updateMethod = UpdateMethod.OVERWRITE;
                } else {
                    IRow<String> row1 = readLines(origCopy);
                    IRow<String> row2 = readLines(file);
                    patchList = FileDiff.createPatchByLines(row1, row2);
                }
                break;
            }
        }

        logger.info("    " + fileInfo.getLocalPath());

        BCharOut buf = new BCharOut(4096);
        ITreeOut out = TreeOutImpl.from(buf);
        build(out, model);
        out.flush();
        String text = buf.toString();

        IRow<String> textRow = new StringRow(new StringSource(text).read().readLines());
        IRow<String> patchedRow = textRow;

        if (patchList != null && patchList.isDifferent()) {
            logger.debug("    patch on " + displayPath);
            PatchApplyResult<String> result = patchList.apply(textRow);
            if (result.isError()) {
                logger.error("Patch failed. ");
                printResult(Stdio.cout.indented(), "Patch failed:", result, null);
                return false;
            }
            patchedRow = result.getPatchedRow();
        }

        if (saveOrig) {
            File origCopy = getOrigCopy(fileInfo);
            IRow<String> origRow = readLines(origCopy);
            if (FileDiff.compareByLines(textRow, origRow).isDifferent()) {
                logger.info("    save original backup at " + origCopy);

                File origDir = origCopy.getParentFile();
                origDir.mkdirs();

                new FileResource(origCopy).write().writeString(textRow);
            }
        }

        StringRow fileRow = readLines(file);
        if (FileDiff.compareByLines(patchedRow, fileRow).isSame())
            return false;

        logger.info("    save " + displayPath);
        new FileResource(file).write().writeString(patchedRow);
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

    private static StringRow readLines(File file) {
        StringRow row = new StringRow();
        if (file.exists()) {
            for (String line : new FileResource(file).read().lines())
                row.append(line);
        }
        return row;
    }

    private boolean byChars = false;

    private boolean chopMode = false;

    private <T> void printResult(ITreeOut out, String message, PatchApplyResult<T> par, IRow<T> expected) {
        out.enterln(message);
        if (expected != null) {
            if (par.getPatchedRow().equals(expected))
                out.println("same as expected.");
            else
                out.println("not as expected. ");
        }

        if (par.isError()) {
            out.enterln("failed hunks: ");
            int i = 0;
            for (PatchApplyStatus<T> item : par) {
                Patch<T> patch = item.patch;
                if (item.isError()) {
                    out.enterln("#" + i + "\t" + patch.diffs.toDelta());
                    for (RowEdit<T> diff : patch.diffs) {
                        out.println(diff.toString());
                    }
                    out.leave();
                }
                i++;
            }
            out.leave();
        }

        out.enterln("content:");
        {
            if (byChars) {
                String s = par.getPatchedRow().toString();
                for (String line : new LinesText.Builder().text(s).removeEOL().trim().build())
                    out.println(line);
            } else {
                @SuppressWarnings("unchecked")
                IRow<String> row = (IRow<String>) par.getPatchedRow();
                for (String line : row)
                    if (chopMode)
                        out.println("| " + line);
                    else
                        out.print("> " + line);
            }
            out.leave();
        }
        out.leave();
    }

}
