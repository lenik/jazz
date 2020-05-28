package net.bodz.lily.security;

public class VerificationCodeGenerator {

    long window;
    int nslot;
    long slotSize;

    int length = 6;
    long modulo;
    long keyHash;

    public VerificationCodeGenerator(long window) {
        this(window, 10);
    }

    public VerificationCodeGenerator(long window, int nslot) {
        this.window = window;
        this.nslot = nslot;
        this.slotSize = window / nslot;
    }

    public VerificationCodeGenerator(long window, float kSafe) {
        this(window, (int) (1.0f / kSafe));
    }

    public VerificationCodeGenerator length(int len) {
        this.length = len;
        long m = 1;
        for (int i = 0; i < len; i++)
            m *= 10;
        this.modulo = m;
        return this;
    }

    static final long bias = 0x12345678_12345678L;
    static final int prime = 45077;

    public VerificationCodeGenerator key(long key) {
        int hash = new Long(key).hashCode();
        this.keyHash = hash * prime;
        return this;
    }

    public VerificationCodeGenerator key(String key) {
        return key(key.hashCode());
    }

    public long getIndex(long time) {
        long index = time / slotSize;
        return index;
    }

    public long generate(long time) {
        long index = getIndex(time);
        return generateForIndex(index);
    }

    public String generate(long time, int radix) {
        long index = getIndex(time);
        return generateForIndex(index, radix);
    }

    public long generateForIndex(long index) {
        long hash = index * prime;
        hash ^= keyHash;
        return hash;
    }

    public String generateForIndex(long index, int radix) {
        long hash = generateForIndex(index);
        String s = Long.toString(hash, radix);
        int n = s.length();
        if (n > length)
            return s.substring(n - length);
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < length; i++)
            sb.append('0');
        sb.append(s);
        return sb.toString();
    }

    int extraSlots = 1;

    public int searchBackwards(String code) {
        return searchBackwards(System.currentTimeMillis(), 10, code);
    }

    public int searchBackwards(long time, int radix, String code) {
        code = code.toLowerCase();
        long index = getIndex(time);
        int validSlots = nslot + extraSlots;
        // search in history slots
        for (int s = -validSlots; s <= 0; s++) {
            String m = generateForIndex(index + s, radix);
            if (code.equals(m))
                return -s;
        }
        return -1;
    }

    public static void main1(String[] args)
            throws Exception {
        int window = 100;
        int slots = 3;
        int delta = window / slots;
        VerificationCodeGenerator vcg = new VerificationCodeGenerator(window, slots);
        for (int i = 0; i < slots * 10; i++) {
            long time = System.currentTimeMillis();
            long idx = vcg.getIndex(time);
            String s = vcg.generate(time, 10);
            System.out.println(idx + ": " + s);
            Thread.sleep(delta / 3);
        }
    }

    public static void main(String[] args)
            throws Exception {
        int window = 10000;
        int slots = 10;
        VerificationCodeGenerator vcg = new VerificationCodeGenerator(window, slots);

        long time = System.currentTimeMillis();
        long index = vcg.getIndex(time);
        String code = vcg.generate(time, 10);
        System.out.printf("Current time %d, index %d, code %s.\n", time, index, code);

        for (int i = 0; i < 10; i++) {
            String c = vcg.generateForIndex(index + i, 10);
            System.out.printf("    idx+%d: %s\n", i, c);
        }

        int s = vcg.searchBackwards(time, 10, "927827");
        System.out.println("Last slot: " + s);
    }

}
