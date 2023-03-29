package net.bodz.bas.io.data;

public class EOTDetector {

    private final int[] eotCharSeq;
    private final int eotLen;
    private int[] ringbuf;
    private int ptr;

    public EOTDetector(String eotCharSeq) {
        this(toInts(eotCharSeq));
    }

    static int[] toInts(String str) {
        int len = str.length();
        int[] ints = new int[len];
        for (int i = 0; i < len; i++)
            ints[i] = str.charAt(i);
        return ints;
    }

    public EOTDetector(int[] eotCharSeq) {
        this.eotCharSeq = eotCharSeq;
        eotLen = eotCharSeq.length;
        ringbuf = new int[eotLen];
        ptr = 0;
    }

    public boolean push(int ch) {
        if (ptr == eotLen) {
            for (int i = 1; i < ptr; i++)
                ringbuf[i - 1] = ringbuf[i];
            ringbuf[ptr] = ch;
        } else {
            ringbuf[ptr++] = ch;
        }

        for (int i = 0; i < eotLen; i++)
            if (eotCharSeq[i] != ringbuf[i])
                return false;

        return true;
    }

    public void reset() {
        ptr = 0;
    }

    public boolean isFull() {
        return ptr == eotLen;
    }

    public int size() {
        return ptr;
    }

    public int shift() {
        if (ptr == 0)
            throw new IllegalStateException("nothing to shift");
        int head = ringbuf[0];
        for (int i = 1; i < eotLen; i++)
            ringbuf[i - 1] = ringbuf[i];
        ptr--;
        return head;
    }

}