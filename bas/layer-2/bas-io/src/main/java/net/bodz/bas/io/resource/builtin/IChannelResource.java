package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.OpenOption;

public interface IChannelResource {

    ByteChannel newByteChannel(OpenOption... options)
            throws IOException;

    SeekableByteChannel newSeekableByteChannel(OpenOption... options)
            throws IOException;

}
