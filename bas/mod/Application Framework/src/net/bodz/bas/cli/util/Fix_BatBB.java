package net.bodz.bas.cli.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.BatchEditCLI;
import net.bodz.bas.cli.EditResult;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.io.Files;

@Doc("Fix: .bat goto labels must not cross 1k block boundary.")
@Version( { 0, 0 })
@RcsKeywords(id = "$Id$")
public class Fix_BatBB extends BatchEditCLI {

    @Option(alias = "b", vnam = "SIZE", doc = "block size, default 1024")
    int  blockSize = 1024;

    @Option
    char fillChar  = ':';

    @Override
    protected EditResult doEditByIO(InputStream in, OutputStream out) throws Exception {
        int start = 0; // out

        ByteBuffer lineBuf = ByteBuffer.allocate(1024);
        byte[] buf = new byte[Files.blockSize];
        int cb;
        boolean leading = true;
        int leadChar = 0;

        while ((cb = in.read(buf, 0, buf.length)) != -1) {
            for (int i = 0; i < cb; i++) {
                byte b = buf[i];
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
        return EditResult.compareAndSave();
    }

    boolean crossBlocks(int start, int end) {
        int startBlock = start / blockSize;
        int endBlock = end / blockSize;
        return startBlock != endBlock;
    }

    static byte[] remPrefix = ":: Fix_BatBB ::".getBytes(); //$NON-NLS-1$

    int fillUps(OutputStream out, int start) throws IOException {
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

    public static void main(String[] args) throws Exception {
        new Fix_BatBB().run(args);
    }

}
