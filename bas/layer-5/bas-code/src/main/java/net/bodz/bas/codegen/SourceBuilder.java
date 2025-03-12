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
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.text.LinesText;
import net.bodz.bas.text.row.IRow;
import net.bodz.bas.text.row.StringRow;

public abstract class SourceBuilder<model_t> {

    static final Logger logger = LoggerFactory.getLogger(SourceBuilder.class);

    protected abstract IFileInfo getFileInfo(model_t model);

    /**
     * @return non-<code>null</code> update method.
     */
    protected UpdateMethod getPreferredUpdateMethod(model_t model) {
        return UpdateMethod.DIFF_PATCH_UPGRADE;
    }

    public final boolean buildFile(model_t model)
            throws IOException {
        return buildFile(model, null);
    }

    public final boolean buildFile(model_t model, UpdateMethod fileUpdateMethod)
            throws IOException {
        UpdateMethod updateMethod = fileUpdateMethod;
        if (updateMethod == null)
            updateMethod = getPreferredUpdateMethod(model);

        IFileInfo fileInfo = getFileInfo(model);
        File file = fileInfo.toFile();
        String displayPath = FilePath.getRelativePath(file.getPath(), fileInfo.getBaseDir().getPath() + "/");

        File dir = file.getParentFile();
        if (!dir.exists())
            if (!dir.mkdirs()) {
                logger.error("error mkdirs " + dir);
                return false;
            }

        PatchList<String> patchList = null;
        StringRow oldGenLines = null;
        StringRow oldFileLines = null;

        if (file.exists()) {
            switch (updateMethod) {
                case NO_UPDATE:
                    return false;

                case OVERWRITE:
                    logger.debug("    overwrite " + displayPath);
                    break;

                case DIFF_MERGE:
                    File origCopy = getOrigFile(fileInfo);
                    if (!origCopy.exists()) {
                        // logger.warn("can't do diff/merge: not able to read " + origCopy);
                        updateMethod = UpdateMethod.OVERWRITE;
                    } else {
                        oldGenLines = readLines(origCopy);
                        oldFileLines = readLines(file);
                        patchList = FileDiff.createPatchByLines(oldGenLines, oldFileLines);
                    }
                    break;

                case DIFF_PATCH_CREATE:
                case DIFF_PATCH_UPGRADE:
                    oldFileLines = readLines(file);
                    break;
            }
        }

        logger.info("    " + fileInfo.getLocalPath());

        BCharOut buf = new BCharOut(4096);
        ITreeOut out = TreeOutImpl.from(buf);

        if (!build(out, model))
            return false;

        out.flush();
        String genText = buf.toString();

        StringRow genLines = new StringRow(new StringSource(genText).read().readLines());
        IRow<String> patchedLines = genLines;

        File patchFile = getPatchFile(fileInfo);
        int patchStatus;

        switch (updateMethod) {
            case DIFF_MERGE:
                if (patchList != null && patchList.isDifferent()) {
                    logger.debug("    patch on " + displayPath);
                    PatchApplyResult<String> result = patchList.apply(genLines);
                    for (; result.isError() && patchList.config.Match_Threshold < 0.9; patchList.config.Match_Threshold += 0.05f) {
                        logger.warn("Patch failed at threshold " + patchList.config.Match_Threshold);
                        result = patchList.apply(genLines);
                    }
                    if (result.isError()) {
                        logger.error("Patch failed. ");
                        printResult(Stdio.cout.indented(), "Patch failed:", result, null);
                        return false;
                    }
                    patchedLines = result.getPatchedRow();
                }
                boolean saveOrigCg = true;
                if (saveOrigCg) {
                    File origCopy = getOrigFile(fileInfo);
                    if (FileDiff.compareByLines(genLines, oldGenLines).isDifferent()) {
                        logger.info("    save original backup at " + origCopy);

                        File origDir = origCopy.getParentFile();
                        origDir.mkdirs();

                        ResFn.file(origCopy).write().writeString(genLines);
                    }
                }
                break;

            case DIFF_PATCH_CREATE:
                patchedLines = oldFileLines;
                break;

            case DIFF_PATCH_UPGRADE:
                if (patchFile.exists()) {
                    // String patchLocalPath = fileInfo.getLocalPath() + ".patch";
                    File tmpGenFile = File.createTempFile(fileInfo.getName(), fileInfo.getDotExtension());
                    ResFn.file(tmpGenFile).write().writeString(genText);

                    Process patchProcess = new ProcessBuilder()//
                            .command("patch", tmpGenFile.getPath()) //
                            .directory(fileInfo.getBaseDir()) //
                            .redirectInput(patchFile) //
                            .start();
                    try {
                        patchStatus = patchProcess.waitFor();
                        if (patchStatus != 0) {
                            logger.errorf("Patch failed (error %d), but result is always saved.", patchStatus);
                        }
                    } catch (InterruptedException e) {
                        throw new IOException(e.getMessage(), e);
                    }

                    StringRow tmpGenPatchedLines = new StringRow();
                    for (String line : ResFn.file(tmpGenFile).read().readLines())
                        tmpGenPatchedLines.append(line);
                    patchedLines = tmpGenPatchedLines;

                    tmpGenFile.delete();
                }
                break;

            default:
        }

        if (oldFileLines == null)
            oldFileLines = readLines(file);

        if (FileDiff.compareByLines(patchedLines, oldFileLines).isDifferent()) {
            logger.info("    save " + displayPath);
            ResFn.file(file).write().writeString(patchedLines);
        }

        boolean updatePatch = false;

        switch (updateMethod) {
            case DIFF_PATCH_CREATE:
                updatePatch = true;
                break;
            case DIFF_PATCH_UPGRADE:
                updatePatch = patchFile.exists();
                break;
            default:
        }

        if (updatePatch) {
            UnixPatchUpdater patchUpdater = new UnixPatchUpdater.Builder() //
                    .baseDir(fileInfo.getBaseDir()) //
                    .file1Temp(fileInfo.getFileName(), genText) //
                    .path2(fileInfo.getLocalPath()) //
                    .modifyPrefix("codegen", "user") //
                    .patchFile(patchFile) //
                    .build();
            patchUpdater //
                    .deleteEmptyPatch() //
                    .ignoreSameBody() //
                    .update();
        }
        return true;
    }

    static File getOrigFile(IFileInfo file) {
        File baseDir = file.getBaseDir();
        File origBaseDir = new File(baseDir.getPath() + ".0");
        String localPath = file.getLocalPath();
        File origCopy = new File(origBaseDir, localPath);
        return origCopy;
    }

    static File getPatchFile(IFileInfo file) {
        File baseDir = file.getBaseDir();
        File patchBaseDir = new File(baseDir.getPath() + ".p");
        String localPath = file.getLocalPath();
        File patchFile = new File(patchBaseDir, localPath + ".patch");
        return patchFile;
    }

    public abstract boolean build(ITreeOut out, model_t model);

    private static StringRow readLines(File file) {
        StringRow row = new StringRow();
        if (file.exists()) {
            for (String line : ResFn.file(file).read().lines())
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
