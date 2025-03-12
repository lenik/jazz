package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.OpenOption;

import net.bodz.bas.meta.decl.NotNull;

public interface IChannelResource {

    @NotNull
    ByteChannel newByteChannel(OpenOption... options)
            throws IOException;

    @NotNull
    SeekableByteChannel newSeekableByteChannel(OpenOption... options)
            throws IOException;

}
