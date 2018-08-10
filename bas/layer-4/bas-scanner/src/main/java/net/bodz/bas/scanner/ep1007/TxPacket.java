package net.bodz.bas.scanner.ep1007;

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
    private CommandStr command;
    private boolean beep;
    private int checksum;
    private boolean end;

    private TxPacket(OutputStream out, CommandStr command)
            throws IOException {
        this.out = out;
        this.command = command;
    }

    public CommandStr getCommand() {
        return command;
    }

    public static TxPacket start(OutputStream out, CommandStr command)
            throws IOException {
        logger.debug("Start " + command);
        TxPacket pkt = new TxPacket(out, command);
        pkt._start();
        return pkt;
    }

    void _start()
            throws IOException {
        writeByte(MessageEndpoint.HOST.code);
        writeByte(MessageEndpoint.DECODER.code);
        writeByte(0); // reserved
        writeByte(0); // reserved
        writeString(command.getStr());
    }

    public boolean isBeep() {
        return beep;
    }

    public void setBeep(boolean beep) {
        this.beep = beep;
    }

    public TxPacket beep() {
        beep = true;
        return this;
    }

    public TxPacket beep(boolean beep) {
        this.beep = beep;
        return this;
    }

    public void end()
            throws IOException {
        if (end)
            throw new IllegalStateException("Already end.");

        writeByte(beep ? 0xFF : 0x31);

        out.write(outbuf.size() & 0xFF);
        out.write(outbuf.toByteArray());
        out.write(~checksum + 1); // checksum not used.
        out.flush();
        end = true;
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
        outbuf.write(lo);
        outbuf.write(hi);
        checksum += hi + lo;
    }

    public void writeDword(int dword)
            throws IOException {
        for (int i = 0; i < 4; i++) {
            int byt = dword & 0xFF;
            dword >>= 8;
            outbuf.write(byt);
            checksum += byt;
        }
    }

    public void writeString(String s)
            throws IOException {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i) & 0xFF;
            outbuf.write(ch);
            checksum += ch;
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
