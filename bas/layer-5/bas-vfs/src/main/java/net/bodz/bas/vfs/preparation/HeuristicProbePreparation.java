package net.bodz.bas.vfs.preparation;

import java.io.IOException;

import net.bodz.bas.io.resource.tools.IStreamReading;
import net.bodz.bas.vfs.IFile;

public class HeuristicProbePreparation
        extends LazyProbePreparation {

    public HeuristicProbePreparation(IFile file) {
        super(file);
    }

    @Override
    public boolean isText()
            throws IOException {
        if (!file.isBlob())
            return false;

        byte[] block;
        try {
            IStreamReading readPrep = file.forRead();
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
