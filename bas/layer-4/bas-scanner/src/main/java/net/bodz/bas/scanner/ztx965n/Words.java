package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;
import java.math.BigInteger;

public class Words
        implements IRxParser {

    int count;
    byte[] data;

    Words() {
    }

    public Words(int count) {
        this.data = new byte[count * 2];
        this.count = count;
    }

    public Words(int... data) {
        if (data == null)
            throw new NullPointerException("data");
        if (data.length % 2 != 0)
            throw new IllegalArgumentException("length isn't even.");
        this.data = new byte[data.length];
        this.count = data.length / 2;
        for (int i = 0; i < data.length; i++)
            this.data[i] = (byte) data[i];
    }

    public Words(byte... data) {
        if (data == null)
            throw new NullPointerException("data");
        if (data.length % 2 != 0)
            throw new IllegalArgumentException("length isn't even.");
        this.data = data;
        this.count = data.length / 2;
    }

    /**
     * @param size
     *            in bytes.
     */
    public Words(String s, int size) {
        int len = s.length();
        byte[] data = new byte[size];
        int pos = len;
        for (int i = size - 1; i >= 0; i--) {
            if (pos <= 0)
                break;
            char lo = s.charAt(--pos);
            char hi = '0';
            if (pos > 0)
                hi = s.charAt(--pos);

            int byt = Integer.parseInt("" + hi + lo, 16);
            data[i] = (byte) byt;
        }
        this.data = data;
        this.count = size / 2;
    }

    @Override
    public Words parse(RxPacket in)
            throws IOException {
        int nword = in.readByte();
        Words words = new Words(nword);
        in.read(words.data);
        return words;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public BigInteger toBigInteger() {
        BigInteger bigInt = BigInteger.ZERO;
        for (int i = 0; i < data.length; i++) {
            int hi = data[i] & 0xFF;
            bigInt = bigInt.multiply(BigInteger.valueOf(256));
            bigInt = bigInt.add(BigInteger.valueOf(hi));
        }
        return bigInt;
    }

    public String printWords() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 2) {
            int hi = data[i] & 0xFF;
            int lo = data[i + 1] & 0xFF;
            int word = hi * 256 + lo;
            if (i != 0)
                sb.append('.');
            sb.append(word);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return memCodec.encode(data);
    }

    public static void main(String[] args) {
        Words w = new Words("123", 4);
        System.out.println(w);
    }

}
