package net.bodz.bas.vfs.util.content;

import java.io.IOException;

import net.bodz.bas.io.resource.tools.IStreamReading;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.vfs.IFile;

public class HeuristicProbing
        extends LazyProbing {

    public HeuristicProbing(IFile file) {
        super(file);
    }

    @Override
    public boolean isText()
            throws IOException {
        if (!file.isBlob())
            return false;

        byte[] block;
        try {
            IStreamReading readPrep = file.tooling()._for(StreamReading.class);
            block = readPrep.readBytes(TextOrBinary.textLookSize);
        } catch (IOException e) {
            return super.isText();
        }
        return TextOrBinary.isText(block);
    }

    @Override
    public boolean isBinary()
            throws IOException {
        return file.isBlob() ? !isText() : false;
    }

}
