package net.bodz.bas.crypto.trans;

public interface IFlyingAuth {

    IFlyingTransient sign(String content);

    FlyingIndex checkSign(String content, String sign);

}
