package net.bodz.bas.cli.skel;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.OpenOption;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.err.ExceptionLog;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.tools.IStreamReading;
import net.bodz.bas.io.resource.tools.IStreamWriting;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.util.TempFile;

public class FileHandler
        implements Closeable {

    private String name;

    private IFile inputFile;
    // private Charset inputCharset;

    private IFile outputDir;
    private IFile outputFile;
    // private Charset outputCharset;

    private boolean writeToTmpBeforeSave;
    private IFile tmpDir = TempFile.getTempRoot();
    private String tmpPrefix;
    private IFile tmpFile;

    private FileHandleResult result = FileHandleResult.IGNORED;
    private ExceptionLog exceptions = new ExceptionLog();;
    private Boolean diff;
    private Set<String> tags = new TreeSet<String>();

    private OpenOption[] inputOpenOptions = {};
    private OpenOption[] outputOpenOptions = {};
    private CopyOption[] copyOptions = {};
    private DeleteOption[] deleteOptions = {};

    private IPrintOut printOut;

    public FileHandler(String name, IFile file) {
        if (name == null)
            throw new NullPointerException("name");
        if (file == null)
            throw new NullPointerException("file");
        this.name = name;
        this.inputFile = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public IFile getInputFile() {
        return inputFile;
    }

    public void setInputFile(IFile inputFile) {
        if (inputFile == null)
            throw new NullPointerException("file");
        this.inputFile = inputFile;
    }

    public IFile getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(IFile outputDir) {
        if (outputFile != null)
            throw new IllegalStateException("Out file is already opened.");
        this.outputDir = outputDir;
    }

    public IFile getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(IFile outputFile) {
        this.outputFile = outputFile;
    }

    public IFile resolveOutputFile() {
        if (outputFile == null) {
            outputFile = outputDir.getChild(name);
        }
        return outputFile;
    }

    public boolean isWriteToTmpBeforeSave() {
        return writeToTmpBeforeSave;
    }

    public void setWriteToTmpBeforeSave(boolean writeToTmpBeforeSave) {
        this.writeToTmpBeforeSave = writeToTmpBeforeSave;
    }

    public IFile getTmpDir() {
        return tmpDir;
    }

    public void setTmpDir(IFile tmpDir) {
        if (tmpDir == null)
            throw new NullPointerException("tmpDir");
        if (tmpFile != null)
            throw new IllegalStateException("Tmp file is already opened.");
        this.tmpDir = tmpDir;
    }

    public String getTmpPrefix() {
        return tmpPrefix;
    }

    public void setTmpPrefix(String tmpPrefix) {
        this.tmpPrefix = tmpPrefix;
    }

    public IFile getTmpFile() {
        return tmpFile;
    }

    public void setTmpFile(IFile tmpFile) {
        this.tmpFile = tmpFile;
    }

    public IFile resolveTmpFile()
            throws IOException {
        if (tmpFile == null) {
            String prefix = tmpPrefix == null ? name : tmpPrefix;
            String dotExt = inputFile.getPath().getExtension(true);
            tmpFile = TempFile.createTempFile(prefix, dotExt, tmpDir);

            Charset charset = outputFile.getPreferredCharset();
            tmpFile.setPreferredCharset(charset);
        }
        return tmpFile;
    }

    public FileHandleResult getResult() {
        return result;
    }

    public void setResult(FileHandleResult result) {
        if (result == null)
            throw new NullPointerException("result");
        this.result = result;
    }

    public void setSaved(Boolean changed) {
        this.result = FileHandleResult.SAVED;
        this.diff = changed;
    }

    public void setDeleted() {
        this.result = FileHandleResult.DELETED;
    }

    public void setRenamedTo(IFile destFile) {
        this.result = FileHandleResult.RENAMED;
        this.outputFile = destFile;
    }

    public void setMovedTo(IFile destFile) {
        this.result = FileHandleResult.MOVED;
        this.outputFile = destFile;
    }

    public void setCopiedTo(IFile destFile) {
        this.result = FileHandleResult.COPIED;
        this.outputFile = destFile;
    }

    public void logException(Throwable exception) {
        getExceptionLog().log(exception);
    }

    public ExceptionLog getExceptionLog() {
        return exceptions;
    }

    public boolean isErrored() {
        return exceptions != null && !exceptions.isEmpty();
    }

    public Boolean getDiff() {
        return diff;
    }

    public void setDiff(Boolean diff) {
        this.diff = diff;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags.clear();
        for (String tag : tags)
            this.tags.add(tag);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public Charset getInputCharset() {
        return inputFile.getPreferredCharset();
    }

    public void setInputCharset(Charset inputCharset) {
        if (inputCharset == null)
            throw new NullPointerException("inputCharset");
        inputFile.setPreferredCharset(inputCharset);
    }

    public Charset getOutputCharset() {
        resolveOutputFile();
        return outputFile.getPreferredCharset();
    }

    public void setOutputCharset(Charset outputCharset) {
        if (outputCharset == null)
            throw new NullPointerException("outputCharset");
        resolveOutputFile();
        outputFile.setPreferredCharset(outputCharset);
    }

    public OpenOption[] getInputOpenOptions() {
        return inputOpenOptions;
    }

    public void setInputOpenOptions(OpenOption... options) {
        this.inputOpenOptions = options;
    }

    public OpenOption[] getOutputOpenOptions() {
        return outputOpenOptions;
    }

    public void setOutputOpenOptions(OpenOption... options) {
        this.outputOpenOptions = options;
    }

    public CopyOption[] getCopyOptions() {
        return copyOptions;
    }

    public void setCopyOptions(CopyOption... options) {
        this.copyOptions = options;
    }

    public DeleteOption[] getDeleteOptions() {
        return deleteOptions;
    }

    public void setDeleteOptions(DeleteOption... options) {
        this.deleteOptions = options;
    }

    public IStreamInputSource getInputSource()
            throws IOException {
        IStreamInputSource inputSource = getInputFile().getInputSource();
        return inputSource;
    }

    public IStreamOutputTarget getOutputTarget() {
        IFile outFile;
        if (writeToTmpBeforeSave)
            try {
                outFile = resolveTmpFile();
            } catch (IOException e) {
                throw new RuntimeException("Failed to init the tmp file.", e);
            }
        else
            outFile = getOutputFile();

        IStreamOutputTarget outputTarget = outFile.getOutputTarget();
        return outputTarget;
    }

    public synchronized IPrintOut getPrintOut()
            throws IOException {
        if (printOut == null) {
            printOut = getOutputTarget().newPrintOut(outputOpenOptions);
        }
        return printOut;
    }

    public IStreamReading read()
            throws IOException {
        IStreamInputSource inputSource = getInputSource();
        StreamReading reading = inputSource.tooling()._for(StreamReading.class);
        reading.setOpenOptions(inputOpenOptions);
        return reading;
    }

    public IStreamWriting write()
            throws IOException {
        IStreamOutputTarget outputTarget = getOutputTarget();
        StreamWriting writing = outputTarget.tooling()._for(StreamWriting.class);
        writing.setOpenOptions(outputOpenOptions);
        return writing;
    }

    public void commit()
            throws IOException {

        close();

        if (writeToTmpBeforeSave) {
            // copy tmp to out.
            if (tmpFile.isExisted()) {
                setSaved(null);
            }
        }

    }

    @Override
    public void close()
            throws IOException {
        if (printOut != null)
            printOut.close();
    }

    @Override
    protected void finalize()
            throws Throwable {
        close();
    }

}
