package net.bodz.bas.scanner.ztx965n;

import java.io.ByteArrayOutputStream;
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
    private ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
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
        out.write(0x40); // SOT
        writeByte(command.code);
    }

    public void end()
            throws IOException {
        if (end)
            throw new IllegalStateException("Already end.");

        byte[] payload = outbuf.toByteArray();
        outbuf.reset();
        int len = payload.length + 1;
        out.write(len);
        out.write(payload);

        checksum += 0x40 + len;
        out.write(~checksum + 1);

        out.flush();
        end = true;
    }

    public void byteParameter(int byt)
            throws IOException {
        writeWord(1);
        writeByte(byt);
    }

    public void wordParameter(int word)
            throws IOException {
        writeWord(2);
        writeWord(word);
    }

    public void dwordParameter(int dword)
            throws IOException {
        writeWord(4);
        writeDword(dword);
    }

    public void parameter(byte[] buf)
            throws IOException {
        parameter(buf, 0, buf.length);
    }

    public void parameter(byte[] buf, int off, int len)
            throws IOException {
        writeWord(len);
        write(buf, off, len);
    }

    public void writeByte(int byt)
            throws IOException {
        outbuf.write(byt);
        checksum += byt;
    }

    public void writeWord(int word)
            throws IOException {
        int hi = (word >> 8) & 0xFF;
        int lo = (word & 0xFF);
        outbuf.write(hi);
        outbuf.write(lo);
        checksum += hi + lo;
    }

    public void writeDword(int dword)
            throws IOException {
        for (int i = 0; i < 4; i++) {
            int byt = (dword >> 24) & 0xFF;
            dword <<= 8;
            outbuf.write(byt);
            checksum += byt;
        }
    }

    public void write(byte[] bytes)
            throws IOException {
        write(bytes, 0, bytes.length);
    }

    public void write(byte[] bytes, int off, int len)
            throws IOException {
        outbuf.write(bytes, off, len);
        checksum += Arrays.sum(bytes, off, off + len);
    }

}
