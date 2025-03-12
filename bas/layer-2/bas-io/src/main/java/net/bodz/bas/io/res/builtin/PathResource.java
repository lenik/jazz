package net.bodz.bas.io.res.builtin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.AbstractIORandomResource;
import net.bodz.bas.meta.decl.NotNull;

public class PathResource
        extends AbstractIORandomResource<PathResource>
        implements IChannelResource {

    private final Path path;

    public PathResource(Path path) {
        if (path == null)
            throw new NullPointerException("path");
        this.path = path;
    }

    public PathResource(String first, String... more) {
        if (first == null)
            throw new NullPointerException("first");
        this.path = Paths.get(first, more);
    }

    public PathResource(URI uri) {
        if (uri == null)
            throw new NullPointerException("uri");
        this.path = Paths.get(uri);
    }

    public Path getPathObject() {
        return path;
    }

    @Override
    public String getPath() {
        return path.toString();
    }

    @Override
    public boolean canRead() {
        return Files.isReadable(path);
    }

    @Override
    public boolean canWrite() {
        return Files.isWritable(path);
    }

    @Override
    public boolean isDirectory(LinkOption... options) {
        return Files.isDirectory(path, options);
    }

    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return Files.newInputStream(path, options);
    }

    @NotNull
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        return Files.newOutputStream(path, options);
    }

    @NotNull
    @Override
    public BufferedReader newBufferedReader(OpenOption... options)
            throws IOException {
        return Files.newBufferedReader(path, getCharset());
    }

    @NotNull
    @Override
    public BufferedWriter newBufferedWriter(OpenOption... options)
            throws IOException {
        return Files.newBufferedWriter(path, getCharset(), options);
    }

    /** ⇱ Implementation Of {@link IChannelResource}. */
    /* _____________________________ */static section.iface __CHANNEL__;

    @NotNull
    @Override
    public final ByteChannel newByteChannel(OpenOption... options)
            throws IOException {
        return newSeekableByteChannel(options);
    }

    @NotNull
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
