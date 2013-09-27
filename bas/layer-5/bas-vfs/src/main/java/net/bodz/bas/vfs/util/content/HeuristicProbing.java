package net.bodz.bas.vfs.util.content;

import java.io.IOException;

import net.bodz.bas.io.res.tools.IStreamReading;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.vfs.IFile;

public class HeuristicProbing
        extends LazyProbing {

    public HeuristicProbing(IFile file) {
        super(file);
    }

    @Override
    public boolean isText()
            throws IOException {
        if (!file.getAttributes().isBlob())
            return false;

        byte[] block;
        try {
            IStreamReading readPrep = file.to(StreamReading.class);
            block = readPrep.read(TextOrBinary.textLookSize);
        } catch (IOException e) {
            return super.isText();
        }
        return TextOrBinary.isText(block);
    }

    @Override
    public boolean isBinary()
            throws IOException {
        return file.getAttributes().isBlob() ? !isText() : false;
    }

}
