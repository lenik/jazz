package net.bodz.lily.security.login;

public interface ITimeoutChallenger {

    String compute();

    String computeAt(long time);

    int rcheck(String code);

    int rcheckAt(long time, String code);

}
