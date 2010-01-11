package net.bodz.bas.fs.preparation;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IStreamReadPreparation {

    int getBlockSize();

    void setBlockSize(int blockSize);

    Charset getCharset();

    InputStream newInputStream()
            throws IOException;

    Reader newReader()
            throws IOException;

    byte[] readBytes()
            throws IOException;

    byte[] readBytes(int maxBytesToRead)
            throws IOException;

    String readString()
            throws IOException;

    String readString(int maxBytesToRead)
            throws IOException;

    List<byte[]> listBlocks(int maxBlocks)
            throws IOException;

    ImmediateIteratorX<byte[], IOException> blockIterator()
            throws IOException;

    List<String> listLines()
            throws IOException;

    List<String> listLines(boolean chopped, int maxLines)
            throws IOException;

    ImmediateIteratorX<String, IOException> lineIterator(boolean chopped)
            throws IOException;

    ZipFile newZipFile()
            throws IOException;

    JarFile newJarFile(boolean verify)
            throws IOException;

}
