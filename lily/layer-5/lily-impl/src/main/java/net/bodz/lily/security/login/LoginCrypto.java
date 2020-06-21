package net.bodz.lily.security.login;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.IFlyingSupport.tr;
import net.bodz.bas.crypto.trans.IFlyingTransient;
import net.bodz.bas.site.json.AbstractJsonResponse;
import net.bodz.lily.security.login.key.ISignatureChecker;

public class LoginCrypto {

    IFlyingTransient core;
    int distance;

    public LoginCrypto(IFlyingTransient core, int distance) {
        this.core = core;
        this.distance = distance;
    }

    public int getTimeout(int unit) {
        long ms = core.getWindow() * distance;
        return (int) (ms / unit);
    }

    public IFlyingTransient shortVerificationCode(String key) {
        return core.transform(tr.md5Sign(key).join(tr.partialMd5(6, 10)));
    }

    public FlyingIndex checkShortVerificationCode(String key, String verificationCode, AbstractJsonResponse<?> resp) {
        IFlyingTransient signTransient = shortVerificationCode(key);
        FlyingIndex fi = signTransient.lastIndexOf(verificationCode, distance);
        if (resp != null)
            if (fi.exists())
                resp.set("fi", fi);
        return fi;
    }

    public IFlyingTransient sign(String content) {
        return core.transform(tr.sha1Sign(content));
    }

    public IFlyingTransient passwordSign(String password) {
        return sign(password);
    }

    public FlyingIndex checkPasswordSign(String text, String signature, AbstractJsonResponse<?> resp) {
        IFlyingTransient signTransient = sign(text);
        FlyingIndex fi = signTransient.lastIndexOf(signature, distance);
        if (resp != null)
            if (fi.exists())
                resp.set("fi", fi);
        return fi;
    }

    class SignChecker
            implements ISignatureChecker {

        @Override
        public FlyingIndex checkSignature(String textData, String signature) {
            return LoginCrypto.this.checkPasswordSign(textData, signature, null);
        }

    }

    SignChecker signChecker = new SignChecker();

}
