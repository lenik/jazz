package net.bodz.bas.vfs.util;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.PathUnsupportedException;
import net.bodz.bas.vfs.impl.nio.NioFile;

public class TempFile {

    static class _TempFile
            extends net.bodz.bas.c.java.io.TempFile {
    }

    public static IFile getTempRoot() {
        Path tempRoot = _TempFile.getTempRoot();
        return new NioFile(tempRoot);
    }

    public static IFile createTempFile()
            throws IOException {
        Path tempFile = _TempFile.createTempFile();
        return new NioFile(tempFile);
    }

    public static IFile createTempFile(String prefix, String suffix)
            throws IOException {
        Path tempFile = _TempFile.createTempFile(prefix, suffix);
        return new NioFile(tempFile);
    }

    public static IFile createTempFile(String prefix, String suffix, IFile directory)
            throws IOException {
        Path localDir = null;
        try {
            localDir = directory.toPath();
        } catch (PathUnsupportedException e) {
            throw new UnsupportedOperationException("Can't create tmp file in non-local tmp dir");
        }
        Path tempFile = _TempFile.createTempFile(prefix, suffix, localDir);
        return new NioFile(tempFile);
    }

    public static IFile createTempDirectory()
            throws IOException {
        Path tempDir = _TempFile.createTempDirectory();
        return new NioFile(tempDir);
    }

    public static IFile createTempDirectory(String prefix, String suffix)
            throws IOException {
        Path tempDir = _TempFile.createTempDirectory(prefix, suffix);
        return new NioFile(tempDir);
    }

    public static IFile createTempDirectory(String prefix, String suffix, IFile directory)
            throws IOException {
        Path localDir = null;
        try {
            localDir = directory.toPath();
        } catch (PathUnsupportedException e) {
            throw new UnsupportedOperationException("Can't create tmp file in non-local tmp dir");
        }
        Path tempDir = _TempFile.createTempDirectory(prefix, suffix, localDir);
        return new NioFile(tempDir);
    }

}
