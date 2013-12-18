package net.bodz.bas.shell.win32;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.program.meta.ProgramName;
import net.bodz.bas.program.skel.BatchEditCLI;
import net.bodz.bas.program.skel.FileHandler;

/**
 * Fix: .bat goto labels must not cross 1k block boundary.
 */
@MainVersion({ 0, 0 })
@ProgramName("fixbatbb")
@RcsKeywords(id = "$Id$")
public class Fix_BatBB
        extends BatchEditCLI {

    /**
     * block size, default 1024
     * 
     * @option -b =SIZE
     */
    int blockSize = 1024;

    /**
     * @option
     */
    char fillChar = ':';

    @Override
    public void processFile(FileHandler handler)
            throws Exception {
        OutputStream out = handler.openOutputStream();

        int start = 0; // out

        ByteBuffer lineBuf = ByteBuffer.allocate(1024);
        boolean leading = true;
        int leadChar = 0;

        for (byte[] block : handler.read().blocks()) {
            for (int i = 0; i < block.length; i++) {
                byte b = block[i];
                lineBuf.put(b);
                if (b == '\n') {
                    lineBuf.flip();
                    int lineLen = lineBuf.limit();
                    if (leadChar == ':') { // label line
                        int end = start + lineLen; // include EOL
                        if (crossBlocks(start, end)) {
                            int fill = fillUps(out, start);
                            start += fill;
                            end += fill;
                        }
                    }
                    out.write(lineBuf.array(), 0, lineLen);
                    start += lineLen;
                    lineBuf.clear();
                    leading = true;
                    leadChar = 0;
                } else if (leading) {
                    if (!Character.isWhitespace(b)) {
                        leadChar = b;
                        leading = false;
                    }
                }
            }
        }

        handler.save();
    }

    boolean crossBlocks(int start, int end) {
        int startBlock = start / blockSize;
        int endBlock = end / blockSize;
        return startBlock != endBlock;
    }

    static byte[] remPrefix = ":: Fix_BatBB ::".getBytes();

    int fillUps(OutputStream out, int start)
            throws IOException {
        int fill = blockSize - start % blockSize;
        int filled = 0;
        while (filled < fill) {
            out.write(remPrefix);
            filled += remPrefix.length;
            int stuff = Math.min(fill - filled - 1, 70);
            if (stuff > 0) {
                filled += stuff;
                while (stuff-- > 0) {
                    out.write(fillChar);
                }
            }
            out.write('\n');
            filled++;
        }
        return filled;
    }

    public static void main(String[] args)
            throws Exception {
        new Fix_BatBB().execute(args);
    }

}
