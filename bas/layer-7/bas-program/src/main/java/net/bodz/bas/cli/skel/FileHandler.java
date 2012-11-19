package net.bodz.bas.cli.skel;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.util.exception.ExceptionLog;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.util.TempFile;

public class FileHandler
        implements Closeable {

    private String name;
    private IFile file;
    private IFile tmpFile;
    private IFile outFile;
    private IFile destFile;

    private IFile tmpDir = TempFile.getTempRoot();
    private String tmpPrefix;
    private IFile outDir;

    private FileHandleResult result = FileHandleResult.ignored;
    private ExceptionLog exceptions = new ExceptionLog();;
    private Boolean diff;
    private Set<String> tags = new TreeSet<String>();

    private boolean appendMode = false;

    private transient InputStream inputStream;
    private transient Reader reader;
    private transient OutputStream outputStream;
    private transient Writer writer;
    private transient OutputStream tempOutputStream;
    private transient Writer tempWriter;

    public FileHandler(String name, IFile file) {
        if (name == null)
            throw new NullPointerException("name");
        if (file == null)
            throw new NullPointerException("file");
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public IFile getFile() {
        return file;
    }

    public void setFile(IFile file) {
        if (file == null)
            throw new NullPointerException("file");
        if (inputStream != null || reader != null)
            throw new IllegalStateException("Input file is already opened.");
        this.file = file;
    }

    public IFile getTmpFile() {
        return tmpFile;
    }

    public void setTmpFile(IFile tmpFile) {
        this.tmpFile = tmpFile;
    }

    public IFile getOrCreateTmpFile()
            throws IOException {
        if (tmpFile == null) {
            String prefix = tmpPrefix == null ? name : tmpPrefix;
            String dotExt = file.getPath().getExtension(true);
            tmpFile = TempFile.createTempFile(prefix, dotExt, tmpDir);
        }
        return tmpFile;
    }

    public IFile getOutFile() {
        return outFile;
    }

    public void setOutFile(IFile outFile) {
        if (outputStream != null || writer != null)
            throw new IllegalStateException("Out file is already opened.");
        this.outFile = outFile;
    }

    public IFile getOrCreateOutFile() {
        if (outFile == null) {
            outFile = outDir.getChild(name);
        }
        return outFile;
    }

    public IFile getDestFile() {
        return destFile;
    }

    public void setDestFile(IFile destFile) {
        this.destFile = destFile;
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

    public IFile getOutDir() {
        return outDir;
    }

    public void setOutDir(IFile outDir) {
        if (outFile != null)
            throw new IllegalStateException("Out file is already opened.");
        this.outDir = outDir;
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
        this.result = FileHandleResult.saved;
        this.diff = changed;
    }

    public void setDeleted() {
        this.result = FileHandleResult.deleted;
    }

    public void setRenamedTo(IFile destFile) {
        this.result = FileHandleResult.renamed;
        this.destFile = destFile;
    }

    public void setMovedTo(IFile destFile) {
        this.result = FileHandleResult.moved;
        this.destFile = destFile;
    }

    public void setCopiedTo(IFile destFile) {
        this.result = FileHandleResult.copied;
        this.destFile = destFile;
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

    public void setTags(Set<String> tags) {
        this.tags = tags;
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
        return file.getPreferredCharset();
    }

    public void setInputCharset(Charset inputCharset) {
        if (inputCharset == null)
            throw new NullPointerException("inputCharset");
        file.setPreferredCharset(inputCharset);
    }

    public Charset getOutputCharset() {
        getOrCreateOutFile();
        return outFile.getPreferredCharset();
    }

    public void setOutputCharset(Charset outputCharset) {
        if (outputCharset == null)
            throw new NullPointerException("outputCharset");
        getOrCreateOutFile();
        outFile.setPreferredCharset(outputCharset);
    }

    public boolean isAppendMode() {
        getOrCreateOutFile();
        return appendMode;
    }

    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    public InputStream openInputStream()
            throws IOException {
        if (inputStream == null) {
            if (reader != null)
                throw new IllegalStateException("Input file is already opened in text mode.");
            inputStream = file.getInputSource().newInputStream();
        }
        return inputStream;
    }

    public Reader openReader()
            throws IOException {
        if (reader == null) {
            if (inputStream != null)
                throw new IllegalStateException("Input file is already opened in binary mode.");
            reader = file.getInputSource().newReader();
        }
        return reader;
    }

    public OutputStream openOutputStream()
            throws IOException {
        if (outputStream == null) {
            if (writer != null)
                throw new IllegalStateException("Output file is already opened in text mode.");
            outputStream = file.getOutputTarget(appendMode).newOutputStream();
        }
        return outputStream;
    }

    public Writer openWriter()
            throws IOException {
        if (writer != null) {
            if (outputStream != null)
                throw new IllegalStateException("Output file is already opened in binary mode.");
            writer = file.getOutputTarget(appendMode).newWriter();
        }
        return writer;
    }

    @Override
    public void close() {
        if (_close(inputStream))
            inputStream = null;
        if (_close(reader))
            reader = null;
        if (_close(outputStream))
            outputStream = null;
        if (_close(writer))
            writer = null;
        if (_close(tempOutputStream))
            tempOutputStream = null;
        if (_close(tempWriter))
            tempWriter = null;
    }

    private boolean _close(Closeable closable) {
        if (closable == null)
            return false;
        try {
            closable.close();
            return true;
        } catch (IOException e) {
            logException(e);
            return false;
        }
    }

    @Override
    protected void finalize() {
        close();
    }

}
