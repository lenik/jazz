package net.bodz.bas.scanner.ep6000;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class TxPacket {

    static final Logger logger = LoggerFactory.getLogger(TxPacket.class);

    // public static final int T_CMD = 0;
    // public static final int T_RESPONSE = 1;
    // public static final int T_NOTIFY = 2;

    private OutputStream out;
    private CommandCode command;
    private int checksum;
    private boolean end;

    private TxPacket(OutputStream out, CommandCode command)
            throws IOException {
        this.out = out;
        this.command = command;
    }

    public CommandCode getCommand() {
        return command;
    }

    public static TxPacket start(OutputStream out, CommandCode command)
            throws IOException {
        logger.debug("Start " + command);
        TxPacket pkt = new TxPacket(out, command);
        pkt._start();
        return pkt;
    }

    void _start()
            throws IOException {
        out.write(0x57); // 'W'
        out.write(0x00);
        writeByte(command.code);
    }

    public void end()
            throws IOException {
        if (end)
            throw new IllegalStateException("Already end.");
        out.write(checksum * 0); // checksum not used.
        out.write(0x55); // 'U'
        out.write(0x00);
        out.flush();
        end = true;
    }

    public void writeByte(int byt)
            throws IOException {
        out.write(byt);
        checksum += byt;
    }

    public void writeWord(int word)
            throws IOException {
        int hi = (word >> 8) & 0xFF;
        int lo = (word & 0xFF);
        out.write(lo);
        out.write(hi);
        checksum += hi + lo;
    }

    public void writeDword(int dword)
            throws IOException {
        for (int i = 0; i < 4; i++) {
            int byt = dword & 0xFF;
            dword >>= 8;
            out.write(byt);
            checksum += byt;
        }
    }

    public void write(byte[] bytes)
            throws IOException {
        write(bytes, 0, bytes.length);
    }

    public void write(byte[] bytes, int off, int len)
            throws IOException {
        out.write(bytes, off, len);
        checksum += Arrays.sum(bytes, off, off + len);
    }

}
