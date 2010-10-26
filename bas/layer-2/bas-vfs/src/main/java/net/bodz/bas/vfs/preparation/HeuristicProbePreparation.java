package net.bodz.bas.vfs.preparation;

import java.io.IOException;

import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.vfs.IFile;

public class HeuristicProbePreparation
        extends LazyProbePreparation {

    private final IStreamReadPreparation readPrep;

    public HeuristicProbePreparation(IFile file) {
        super(file);
        this.readPrep = file.forRead();
    }

    @Override
    public boolean isText()
            throws IOException {
        byte[] block;
        try {
            block = readPrep.readBytes(TextOrBinary.textLookSize);
        } catch (IOException e) {
            return super.isText();
        }
        return TextOrBinary.isText(block);
    }

    @Override
    public boolean isBinary()
            throws IOException {
        return !isText();
    }

}
