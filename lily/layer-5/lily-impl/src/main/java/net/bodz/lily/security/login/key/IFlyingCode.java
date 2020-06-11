package net.bodz.lily.security.login.key;

public interface IFlyingCode {

    /**
     * size of the time window in ms.
     */
    long getWindow();

    String getCodeForNow();

    String getCodeAtTime(long time);

    String getCode(long index);

    FlyingIndex lastIndexOf(String code, int distance);

    FlyingIndex lastIndexOf(long fromIndex, String code, int distance);

}
