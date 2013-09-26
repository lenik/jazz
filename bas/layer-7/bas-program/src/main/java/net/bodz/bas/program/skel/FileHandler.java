package net.bodz.bas.program.skel;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.err.ExceptionLog;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.UnexpectedEnumException;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.builtin.BytesResource;
import net.bodz.bas.io.res.builtin.CharsResource;
import net.bodz.bas.io.res.tools.IStreamReading;
import net.bodz.bas.io.res.tools.IStreamWriting;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.t.buffer.MovableByteBuffer;
import net.bodz.bas.t.buffer.MovableCharBuffer;
import net.bodz.bas.text.diff.DiffEntry;
import net.bodz.bas.text.diff.IDiffComparator;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.facade.DefaultVfsFacade;
import net.bodz.bas.vfs.facade.IVfsFacade;
import net.bodz.bas.vfs.util.TempFile;

public class FileHandler
        implements Closeable {

    private String pathSpec;
    private IFile sourceFile;
    private IFile targetFile;

    private OutputBufferMode bufferMode;
    private MovableByteBuffer byteBuffer;
    private MovableCharBuffer charBuffer;
    private IFile tmpDir = TempFile.getTempRoot();
    private String tmpPrefix = "~handler~";
    private IFile tmpFile;

    private IStreamInputSource inputSource;

    private IStreamOutputTarget outputTarget;
    private OutputOpenMode outputOpenMode;
    private IPrintOut printOut;
    private OutputStream outputStream;
    private PrintStream printStream;

    private FileHandleMethod method = FileHandleMethod.NONE;
    private boolean modified = false;
    private IDiffComparator diffComparator;
    private List<DiffEntry> diffs;
    private final ExceptionLog exceptions = new ExceptionLog();

    private final Set<String> tags = new TreeSet<String>();

    // private Charset inputCharset;
    // private Charset outputCharset;
    private OpenOption[] inputOpenOptions = {};
    private OpenOption[] outputOpenOptions = {};
    private CopyOption[] copyOptions = {};
    private DeleteOption[] deleteOptions = {};

    private IVfsFacade vfs = DefaultVfsFacade.getInstance();

    public FileHandler(String pathSpec, IFile sourceFile, IFile targetFile) {
        if (sourceFile == null)
            throw new NullPointerException("sourceFile");
        this.pathSpec = pathSpec;
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
    }

    public FileHandler(IStreamInputSource inputSource, IStreamOutputTarget outputTarget) {
        if (inputSource == null)
            throw new NullPointerException("inputSource");
        if (outputTarget == null)
            throw new NullPointerException("outputTarget");
        this.inputSource = inputSource;
        this.outputTarget = outputTarget;
    }

    public String getPathSpec() {
        return pathSpec;
    }

    public void setPathSpec(String pathSpec) {
        this.pathSpec = pathSpec;
    }

    public IFile getSourceFile() {
        return sourceFile;
    }

    public IFile getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(IFile targetFile) {
        if (bufferMode != null)
            if (bufferMode != OutputBufferMode.DIRECT)
                throw new IllegalStateException("there is buffer attached.");

        if (outputOpenMode != null)
            throw new IllegalStateException("already opened.");

        this.targetFile = targetFile;
    }

    public IFile getInputFile() {
        return getSourceFile();
    }

    public IFile getOutputFile()
            throws IOException {

        switch (getBufferMode()) {
        case DIRECT:
            return targetFile;

        case TMP_FILE:
            return getTmpFile();

        case CHAR_BUFFER:
        case BYTE_BUFFER:
        default:
            throw new IllegalUsageException("No output file");
        }
    }

    /**
     * If output buffer mode isn't set, it's determined on the value of the targetDir:
     * <ul>
     * <li>If <code>targetDir</code> isn't specified, then byte buffer is used.
     * <li>If <code>targetDir</code> is the same to the <code>sourceDir</code>, then tmp file is
     * used.
     * <li>Otherwise, no buffer mode is used.
     * </ul>
     */
    public synchronized OutputBufferMode getBufferMode() {
        if (bufferMode == null) {
            if (targetFile == null)
                bufferMode = OutputBufferMode.BYTE_BUFFER;

            else if (outputTarget != null)
                // inputSource -> outputTarget
                bufferMode = OutputBufferMode.DIRECT;

            else {
                boolean sameFile;
                sameFile = targetFile.equals(sourceFile);
                if (sameFile)
                    // src -> tmp -> src
                    bufferMode = OutputBufferMode.TMP_FILE;
                else
                    // src -> target
                    bufferMode = OutputBufferMode.DIRECT;
            }
        }
        return bufferMode;
    }

    /**
     * Set the output buffer mode. Once the mode is set, it can' t be changed in future.
     * 
     * @param bufferMode
     *            The new output buffer mode to set.
     * @throws IllegalStateException
     *             If the output buffer mode is already set, and the new mode is different to the
     *             previous mode.
     */
    public synchronized void setBufferMode(OutputBufferMode bufferMode) {
        if (bufferMode == null)
            throw new NullPointerException("outputBufferMode");

        if (this.bufferMode != null)
            if (this.bufferMode != bufferMode)
                throw new IllegalStateException("Output buffer mode is already set, can't change.");

        this.bufferMode = bufferMode;
    }

    void setPreferredBufferMode(OutputBufferMode bufferMode) {
        // Optim: for buffered-output, preferred to use CharBuffer.
        if (this.bufferMode == null && targetFile == null)
            this.bufferMode = bufferMode;
    }

    public synchronized MovableByteBuffer getByteBuffer() {
        setBufferMode(OutputBufferMode.BYTE_BUFFER);
        if (byteBuffer == null)
            byteBuffer = new MovableByteBuffer();
        return byteBuffer;
    }

    public synchronized MovableCharBuffer getCharBuffer() {
        setBufferMode(OutputBufferMode.CHAR_BUFFER);
        if (charBuffer == null)
            charBuffer = new MovableCharBuffer();
        return charBuffer;
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
        if (tmpPrefix == null)
            throw new NullPointerException("tmpPrefix");
        this.tmpPrefix = tmpPrefix;
    }

    synchronized IFile getTmpFile()
            throws IOException {
        if (tmpFile == null)
            tmpFile = createTmpFile();
        return tmpFile;
    }

    IFile createTmpFile()
            throws IOException {
        String prefix = tmpPrefix == null ? pathSpec : tmpPrefix;
        String dotExt = sourceFile.getPath().getExtension(true);
        IFile tmpFile = TempFile.createTempFile(prefix, dotExt, tmpDir);

        if (targetFile != null) {
            Charset charset = targetFile.getPreferredCharset();
            tmpFile.setPreferredCharset(charset);
        }
        return tmpFile;
    }

    public FileHandleMethod getMethod() {
        return method;
    }

    public void setMethod(FileHandleMethod method) {
        if (method == null)
            throw new NullPointerException("method");
        this.method = method;
    }

    public boolean isModified() {
        return modified;
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

    public IDiffComparator getDiffComparator() {
        return diffComparator;
    }

    public void setDiffComparator(IDiffComparator diffComparator) {
        this.diffComparator = diffComparator;
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
        return sourceFile.getPreferredCharset();
    }

    public void setInputCharset(Charset inputCharset) {
        if (inputCharset == null)
            throw new NullPointerException("inputCharset");
        sourceFile.setPreferredCharset(inputCharset);
    }

    public Charset getOutputCharset() {
        if (targetFile == null)
            return null;
        else
            return targetFile.getPreferredCharset();
    }

    public void setOutputCharset(Charset outputCharset) {
        if (outputCharset == null)
            throw new NullPointerException("outputCharset");
        if (targetFile == null)
            throw new IllegalUsageException("No output..");
        targetFile.setPreferredCharset(outputCharset);
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

    public synchronized IStreamInputSource getInputSource() {
        if (inputSource == null) {
            if (sourceFile == null)
                throw new IllegalStateException("Source file isn't set");
            inputSource = sourceFile.getInputSource();
        }
        return inputSource;
    }

    public void setInputSource(IStreamInputSource inputSource) {
        if (inputSource == null)
            throw new NullPointerException("inputSource");

        if (this.inputSource != null)
            throw new IllegalStateException("Input source is already set.");

        this.inputSource = inputSource;
    }

    public synchronized IStreamOutputTarget getOutputTarget()
            throws IOException {
        if (outputTarget == null)
            switch (getBufferMode()) {
            case DIRECT:
                outputTarget = getTargetFile().getOutputTarget();
                break;

            case TMP_FILE:
                outputTarget = getTmpFile().getOutputTarget();
                break;

            case BYTE_BUFFER:
                outputTarget = new BytesResource(getByteBuffer());
                break;

            case CHAR_BUFFER:
                outputTarget = new CharsResource(getCharBuffer());
                break;

            default:
                assert false;
            }
        return outputTarget;
    }

    public void setOutputTarget(IStreamOutputTarget outputTarget) {
        if (outputTarget == null)
            throw new NullPointerException("outputTarget");

        if (bufferMode != null)
            if (bufferMode != OutputBufferMode.DIRECT)
                throw new IllegalStateException("there is buffer attached.");

        if (outputOpenMode != null)
            throw new IllegalStateException("already opened.");

        this.outputTarget = outputTarget;
    }

    // Open states

    private void setOutputOpenMode(OutputOpenMode mode) {
        if (mode == null)
            throw new NullPointerException("mode");

        if (outputOpenMode != null)
            if (outputOpenMode != mode)
                throw new IllegalStateException("Output has been opened in another type.");

        outputOpenMode = mode;
    }

    public synchronized IPrintOut openPrintOut()
            throws IOException {
        if (printOut == null) {
            setPreferredBufferMode(OutputBufferMode.CHAR_BUFFER);
            setOutputOpenMode(OutputOpenMode.PRINT_OUT);
            printOut = getOutputTarget().newPrintOut(outputOpenOptions);
        }
        return printOut;
    }

    public synchronized OutputStream openOutputStream()
            throws IOException {
        if (outputStream == null) {
            setPreferredBufferMode(OutputBufferMode.BYTE_BUFFER);
            setOutputOpenMode(OutputOpenMode.OUTPUT_STREAM);
            outputStream = getOutputTarget().newOutputStream(outputOpenOptions);
        }
        return outputStream;
    }

    public synchronized PrintStream openPrintStream()
            throws IOException {
        if (printStream == null) {
            setOutputOpenMode(OutputOpenMode.PRINT_STREAM);
            printStream = getOutputTarget().newPrintStream(outputOpenOptions);
        }
        return printStream;
    }

    public IStreamReading read()
            throws IOException {
        IStreamInputSource inputSource = getInputSource();
        StreamReading reading = inputSource.to(StreamReading.class);
        reading.setOpenOptions(inputOpenOptions);
        return reading;
    }

    public IStreamWriting write()
            throws IOException {
        IStreamOutputTarget outputTarget = getOutputTarget();

        setOutputOpenMode(OutputOpenMode.STREAM_WRITING);
        StreamWriting writing = outputTarget.to(StreamWriting.class);
        writing.setOpenOptions(outputOpenOptions);

        return writing;
    }

    public void save() {
        save(null);
    }

    public void save(Boolean modified) {
        if (targetFile == null)
            throw new IllegalUsageException("No target file to save.");

        method = FileHandleMethod.MODIFY;

        try {
            close();

            switch (getBufferMode()) {
            case DIRECT:
                if (sourceFile != null && targetFile != null) {

                    if (!targetFile.isExisted())
                        throw new IllegalStateException("Target file isn't created.");

                    modified = checkDiffOnce(sourceFile, targetFile);
                }
                break;

            case TMP_FILE:
                if (!tmpFile.isExisted())
                    throw new IllegalStateException("No output (to tmp file).");

                modified = checkDiffOnce(tmpFile, targetFile);
                if (modified)
                    // vfs.move(tmpFile, targetFile, copyOptions);
                    vfs.copy(tmpFile, targetFile, copyOptions);
                break;

            case BYTE_BUFFER:
                MovableByteBuffer byteBuffer = getByteBuffer();
                OutputStream targetOut = targetFile.getOutputTarget().newOutputStream(outputOpenOptions);
                try {
                    byteBuffer.writeTo(targetOut);
                } finally {
                    targetOut.close();
                }
                break;

            case CHAR_BUFFER:
                MovableCharBuffer charBuffer = getCharBuffer();
                Writer targetWriter = targetFile.getOutputTarget().newWriter(outputOpenOptions);
                try {
                    charBuffer.writeTo(targetWriter);
                } finally {
                    targetWriter.close();
                }
                break;

            default:
                assert false;
            }
        } catch (IOException e) {
            logException(e);
        }

        if (modified == null) {
            // compare source file and target file.

            if (sourceFile == null || targetFile == null)
                throw new IllegalUsageException("No file to compare, the modified should not be null.");

            try {
                modified = !FileDiff.equals(sourceFile, targetFile);
            } catch (IOException e) {
                logException(e);
            }
        }

        if (modified != null)
            this.modified = modified;
    }

    synchronized boolean checkDiffOnce(IFile source, IFile target)
            throws IOException {
        if (diffComparator == null)
            return FileDiff.equals(source, target);
        else {
            if (diffs == null)
                diffs = FileDiff.compareDiff(source, target, diffComparator);
            return !diffs.isEmpty();
        }
    }

    public synchronized List<DiffEntry> diffOnce()
            throws IOException {
        if (diffs == null) {
            if (diffComparator == null)
                throw new IllegalUsageException("Diff comparator isn't set.");

            IStreamInputSource source1 = sourceFile.getInputSource();
            IStreamInputSource source2;

            switch (getBufferMode()) {
            case DIRECT:
                source2 = targetFile.getInputSource();
                break;

            case TMP_FILE:
                source1 = tmpFile.getInputSource();
                source2 = targetFile.getInputSource();
                break;

            case BYTE_BUFFER:
                source2 = new BytesResource(getByteBuffer());
                break;

            case CHAR_BUFFER:
                source2 = new CharsResource(getCharBuffer());
                break;

            default:
                throw new UnexpectedEnumException();
            }
            diffs = FileDiff.compareDiff(source1, source2, diffComparator);
        }
        return diffs;
    }

    public void copy() {
        method = FileHandleMethod.COPY;

        try {
            close();

            modified = checkDiffOnce(sourceFile, targetFile);
            if (modified)
                vfs.copy(sourceFile, targetFile, copyOptions);

        } catch (IOException e) {
            logException(e);
        }
    }

    public void move() {
        method = FileHandleMethod.MOVE;

        try {
            close();

            // modified = checkDiffOnce(sourceFile, targetFile);
            modified = true;
            if (modified)
                vfs.move(sourceFile, targetFile, copyOptions);
            else
                sourceFile.delete(deleteOptions);

        } catch (IOException e) {
            logException(e);
        }
    }

    public void delete() {
        method = FileHandleMethod.DELETE;

        try {
            close();
            // vfs.delete(sourceFile);
            sourceFile.delete(deleteOptions);
        } catch (IOException e) {
            logException(e);
        }
    }

    @Override
    public void close()
            throws IOException {
        if (printOut != null) {
            printOut.close();
            printOut = null;
        }

        if (outputStream != null) {
            outputStream = null;
            outputStream = null;
        }

        if (printStream == null) {
            printStream.close();
            printStream = null;
        }
    }

    @Override
    protected void finalize()
            throws IOException {
        close();
    }

}
