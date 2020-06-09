package net.bodz.lily.security.login;

public abstract class AbstractTimeoutChallenger
        implements ITimeoutChallenger {

    long timeWindow;
    int nslot;
    long slotSize;
    int extraSlots = 1;
    String key;

    public AbstractTimeoutChallenger(long window) {
        this(window, 10);
    }

    public AbstractTimeoutChallenger(long window, int nslot) {
        this.timeWindow = window;
        this.nslot = nslot;
        this.slotSize = window / nslot;
    }

    @Override
    public long getWindow() {
        return timeWindow;
    }

    long getIndex(long time) {
        long index = time / slotSize;
        return index;
    }

    @Override
    public final String getCodeForNow() {
        return getCodeAtTime(System.currentTimeMillis());
    }

    @Override
    public String getCodeAtTime(long time) {
        long index = getIndex(time);
        return getCode(index);
    }

    @Override
    public abstract String getCode(long index);

    @Override
    public int rcheck(String code) {
        return rcheckAtTime(System.currentTimeMillis(), code);
    }

    @Override
    public int rcheckAtTime(long time, String code) {
        code = code.toLowerCase();
        long index = getIndex(time);
        int validSlots = nslot + extraSlots;
        // search in history slots
        for (int s = -validSlots; s <= 0; s++) {
            String m = getCode(index + s);
            if (code.equals(m))
                return -s;
        }
        return -1;
    }

}
