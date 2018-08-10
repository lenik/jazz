package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

public class SelectFilter
        implements IRxParser {

    int start;
    int length;
    byte[] mask;

    /**
     * in bits.
     */
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    /**
     * in bits.
     */
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getMask() {
        return mask;
    }

    public void setMask(byte[] mask) {
        this.mask = mask;
    }

    @Override
    public SelectFilter parse(RxPacket in)
            throws IOException {
        start = in.readWord();
        length = in.readWord();
        mask = in.readData();
        return this;
    }

    public void send(TxPacket pkt)
            throws IOException {
        pkt.writeWord(start);
        pkt.writeWord(length);
        pkt.write(mask);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mask start at " + start + " bits for " + length + " bits: ");
        sb.append(memCodec.encode(mask));
        return sb.toString();
    }

}
