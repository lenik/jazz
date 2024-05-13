package net.bodz.bas.codegen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.process.MyProcessBuilder;
import net.bodz.bas.io.process.ProcessWrapper;
import net.bodz.bas.io.res.ResFn;

public class UnixPatchUpdater {

    File baseDir;

    File file1;
    String file1Path;
    boolean tmp1;

    File file2;
    String file2Path;
    boolean tmp2;

    File patchFile;
    Charset charset;
    String prefix1;
    String prefix2;

    boolean deleteEmptyPatch;
    boolean ignoreSameBody;

    UnixPatchUpdater(File baseDir, File file1, String file1Path, boolean tmp1, File file2, String file2Path,
            boolean tmp2, File patchFile, Charset charset, String prefix1, String prefix2) {
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        if (file1Path == null)
            throw new NullPointerException("file1Path");
        if (file2Path == null)
            throw new NullPointerException("file2Path");
        if (patchFile == null)
            throw new NullPointerException("patchFile");
        this.baseDir = baseDir;
        this.file1 = file1;
        this.file1Path = file1Path;
        this.tmp1 = tmp1;
        this.file2 = file2;
        this.file2Path = file2Path;
        this.tmp2 = tmp2;
        this.patchFile = patchFile;
        this.charset = charset;
        this.prefix1 = prefix1;
        this.prefix2 = prefix2;
    }

    public UnixPatchUpdater deleteEmptyPatch() {
        deleteEmptyPatch = true;
        return this;
    }

    public UnixPatchUpdater ignoreSameBody() {
        ignoreSameBody = true;
        return this;
    }

    public void update()
            throws IOException {
        File patchDir = patchFile.getParentFile();
        if (! patchDir.exists())
            patchDir.mkdirs();

        String oldPatchText = null;
        if (patchFile.exists())
            oldPatchText = ResFn.file(patchFile, charset).read().readString();

        ByteArrayOutputStream newPatchData = new ByteArrayOutputStream();
        ProcessWrapper diffProcess = new MyProcessBuilder() //
                .command("diff", "-u", file1Path, file2Path) //
                .directory(baseDir) //
                .captureOutput((_buf, off, len) -> {
                    newPatchData.write(_buf, off, len);
                }) //
                .start();
        try {
            int diffStatus = diffProcess.waitFor(true);
            if (diffStatus == 0 && deleteEmptyPatch) {
                patchFile.delete();
            } else {
                // update the patch file.
                String newPatchText = new String(newPatchData.toByteArray(), charset);

                if (ignoreSameBody && UnixPatches.samePatchBody(oldPatchText, newPatchText)) {
                } else {
                    if (prefix1 != null && prefix2 != null)
                        newPatchText = UnixPatches.modifyDiffPath(newPatchText, prefix1, prefix2);
                    ResFn.file(patchFile).write().write(newPatchText.getBytes(charset));
                }
            }
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    @Override
    protected void finalize()
            throws Throwable {
        if (tmp1)
            file1.delete();
        if (tmp2)
            file2.delete();
    }

    public static class Builder {

        File baseDir;

        File file1;
        String path1;
        String file1Content;
        boolean tmp1;

        File file2;
        String path2;
        String file2Content;
        boolean tmp2;

        File patchFile;
        Charset charset = Charset.defaultCharset();
        String prefix1;
        String prefix2;

        public Builder baseDir(File baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        public Builder file1(File file1) {
            this.file1 = file1;
            return this;
        }

        public Builder path1(String path1) {
            this.path1 = path1;
            return this;
        }

        public Builder file1Content(String file1Content) {
            this.file1Content = file1Content;
            return this;
        }

        public Builder file1Temp(String fileNameLike, String content)
                throws IOException {
            file1 = makeTempLike(fileNameLike, content);
            file1Content = content;
            tmp1 = true;
            return this;
        }

        public Builder file2(File file2) {
            this.file2 = file2;
            return this;
        }

        public Builder path2(String path2) {
            this.path2 = path2;
            return this;
        }

        public Builder file2Content(String file2Content) {
            this.file2Content = file2Content;
            return this;
        }

        public Builder file2Temp(String fileNameLike, String content)
                throws IOException {
            file2 = makeTempLike(fileNameLike, content);
            tmp2 = true;
            return this;
        }

        public Builder patchFile(File patchFile) {
            this.patchFile = patchFile;
            return this;
        }

        public Builder charset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public Builder prefix1(String prefix1) {
            this.prefix1 = prefix1;
            return this;
        }

        public Builder prefix2(String prefix2) {
            this.prefix2 = prefix2;
            return this;
        }

        public Builder modifyPrefix(String prefix1, String prefix2) {
            this.prefix1 = prefix1;
            this.prefix2 = prefix2;
            return this;
        }

        public UnixPatchUpdater build() {
            if (baseDir == null)
                throw new NullPointerException("baseDir");

            if (file1 == null && path1 == null)
                throw new NullPointerException("file1 or path1");
            if (file1 == null)
                file1 = new File(baseDir, path1);
            if (path1 == null)
                path1 = file1.getPath();

            if (file2 == null && path2 == null) {
            } else {
                if (file2 == null)
                    file2 = new File(baseDir, path2);
                if (path2 == null)
                    path2 = file2.getPath();
            }

            return new UnixPatchUpdater(baseDir, file1, path1, tmp1, file2, path2, tmp2, patchFile, charset, prefix1,
                    prefix2);
        }

        static File makeTempLike(String path, String content)
                throws IOException {
            String baseName = FilePath.getBaseName(path);
            int lastDot = baseName.lastIndexOf('.');
            String name = lastDot == -1 ? baseName : baseName.substring(0, lastDot);
            String dotExtension = lastDot == -1 ? "" : baseName.substring(lastDot);
            File tempFile = File.createTempFile(name, dotExtension);
            if (content != null)
                ResFn.file(tempFile).write().writeString(content);
            return tempFile;
        }

    }

}
