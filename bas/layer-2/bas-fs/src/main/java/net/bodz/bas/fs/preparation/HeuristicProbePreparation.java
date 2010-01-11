package net.bodz.bas.fs.preparation;

import java.io.IOException;

import net.bodz.bas.fs.IFile;

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
