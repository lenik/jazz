package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.pojf.PojfFile;

public class TempFile {

    static class _TempFile
            extends net.bodz.bas.c.java.io.TempFile {
    }

    public static IFile getTempRoot() {
        File tempRoot = _TempFile.getTempRoot();
        return new PojfFile(tempRoot);
    }

    public static IFile createTempFile()
            throws IOException {
        File tempFile = _TempFile.createTempFile();
        return new PojfFile(tempFile);
    }

    public static IFile createTempFile(String prefix, String suffix)
            throws IOException {
        File tempFile = _TempFile.createTempFile(prefix, suffix);
        return new PojfFile(tempFile);
    }

    public static IFile createTempFile(String prefix, String suffix, IFile directory)
            throws IOException {
        if (directory instanceof PojfFile) {
            File localDir = ((PojfFile) directory).getInternalFile();
            File tempFile = _TempFile.createTempFile(prefix, suffix, localDir);
            return new PojfFile(tempFile);
        } else {
            // return createTempFile(prefix, suffix);
            throw new UnsupportedOperationException("Can't create tmp file in non-local tmp dir");
        }
    }

    public static IFile createTempDirectory()
            throws IOException {
        File tempDir = _TempFile.createTempDirectory();
        return new PojfFile(tempDir);
    }

    public static IFile createTempDirectory(String prefix, String suffix)
            throws IOException {
        File tempDir = _TempFile.createTempDirectory(prefix, suffix);
        return new PojfFile(tempDir);
    }

    public static IFile createTempDirectory(String prefix, String suffix, IFile directory)
            throws IOException {
        if (directory instanceof PojfFile) {
            File localDir = ((PojfFile) directory).getInternalFile();
            File tempDir = _TempFile.createTempDirectory(prefix, suffix, localDir);
            return new PojfFile(tempDir);
        } else {
            // return createTempDirectory(prefix, suffix);
            throw new UnsupportedOperationException("Can't create tmp file in non-local tmp dir");
        }
    }

}
