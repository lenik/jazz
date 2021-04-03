package net.bodz.bas.io.res.builtin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.AbstractIORandomResource;

public class PathResource
        extends AbstractIORandomResource
        implements IChannelResource {

    private Path path;

    public PathResource(Path path) {
        if (path == null)
            throw new NullPointerException("path");
        this.path = path;
    }

    @Override
    public boolean isCharInPreferred() {
        return false;
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return Files.newInputStream(path, options);
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        return Files.newOutputStream(path, options);
    }

    @Override
    protected BufferedReader _newBufferedReader(OpenOption... options)
            throws IOException {
        return Files.newBufferedReader(path, getCharset());
    }

    @Override
    protected BufferedWriter _newBufferedWriter(OpenOption... options)
            throws IOException {
        return Files.newBufferedWriter(path, getCharset(), options);
    }

    /** ⇱ Implementation Of {@link IChannelResource}. */
    /* _____________________________ */static section.iface __CHANNEL__;

    @Override
    public final ByteChannel newByteChannel(OpenOption... options)
            throws IOException {
        return newSeekableByteChannel(options);
    }

    @Override
    public SeekableByteChannel newSeekableByteChannel(OpenOption... options)
            throws IOException {
        return Files.newByteChannel(path, options);
    }

    // XXX byte/char IOS, seeker, cropper

    @Override
    protected ISeekable getSeeker() {
        return null;
    }

    @Override
    protected ICroppable getCropper() {
        return null;
    }

}
