package net.bodz.bas.fs;

import java.io.IOException;

public class HeuristicProbeToolkit
        extends LazyProbeToolkit {

    private final IReadToolkit rt;

    public HeuristicProbeToolkit(IFile file) {
        super(file);
        this.rt = file.forRead();
    }

    @Override
    public boolean isText()
            throws IOException {
        byte[] block;
        try {
            block = rt.readBytes(TextOrBinary.textLookSize);
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
