package net.bodz.lily.security.login;

public interface ITimeoutChallenger {

    long getWindow();

    String getCodeForNow();

    String getCodeAtTime(long time);

    String getCode(long index);

    int rcheck(String code);

    int rcheckAtTime(long time, String code);

}
