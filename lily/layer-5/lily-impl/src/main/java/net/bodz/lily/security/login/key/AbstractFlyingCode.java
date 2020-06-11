package net.bodz.lily.security.login.key;


public abstract class AbstractFlyingCode
        implements IFlyingCode {

    long window;

    public AbstractFlyingCode(long window) {
        this.window = window;
    }

    @Override
    public long getWindow() {
        return window;
    }

    @Override
    public final String getCodeForNow() {
        return getCodeAtTime(System.currentTimeMillis());
    }

    @Override
    public String getCodeAtTime(long time) {
        long index = time / window;
        return getCode(index);
    }

    @Override
    public abstract String getCode(long index);

    @Override
    public FlyingIndex lastIndexOf(String code, int distance) {
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, code, distance);
    }

    @Override
    public FlyingIndex lastIndexOf(long fromIndex, String code, int distance) {
        long pos = fromIndex;
        for (int i = 0; i < distance; i++) {
            String c = getCode(pos);
            if (code.equals(c))
                return new FlyingIndex(pos, window);
            pos--;
        }
        return FlyingIndex.NULL;
    }

}
