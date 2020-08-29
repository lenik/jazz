package net.bodz.lily.security.login;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.IFlyingTransient;
import net.bodz.bas.site.json.AbstractJsonResponse;

public class LoginCrypto
        implements ILoginCryptoConsts {

    IFlyingTransient core;
    int distance;
    int allowAhead;

    public LoginCrypto(IFlyingTransient core, int distance, int allowAhead) {
        this.core = core;
        this.distance = distance;
        this.allowAhead = allowAhead;
    }

    public int getTimeout(int unit) {
        long ms = core.getWindow() * distance;
        return (int) (ms / unit);
    }

    public IFlyingTransient shortVerificationCode(String key) {
        return core.transform(tr.md5Sign(key).join(SHORT_TEXT));
    }

    public FlyingIndex checkShortVerificationCode(String key, String verificationCode, AbstractJsonResponse<?> resp) {
        IFlyingTransient signTransient = shortVerificationCode(key);
        FlyingIndex fi = signTransient.lastIndexOf(verificationCode, distance, allowAhead);
        if (resp != null)
            if (fi.exists())
                resp.setHeader("fi", fi);
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
        FlyingIndex fi = signTransient.lastIndexOf(signature, distance, allowAhead);
        if (fi.exists()) {
            if (resp != null)
                resp.setHeader("fi", fi);
        } else {
            IFlyingTransient ft = signTransient;
            ft.diag(signature, 10, 10);
        }
        return fi;
    }

    class PasswordSignChecker
            implements ISignatureChecker {

        @Override
        public FlyingIndex checkSignature(String textData, String signature) {
            return LoginCrypto.this.checkPasswordSign(textData, signature, null);
        }

    }

    class ShortVerificationCodeChecker
            implements ISignatureChecker {

        @Override
        public FlyingIndex checkSignature(String textData, String signature) {
            return LoginCrypto.this.checkShortVerificationCode(textData, signature, null);
        }

    }

    ISignatureChecker passwordSignChecker = new PasswordSignChecker();
    ISignatureChecker shortVerificationCodeChecker = new ShortVerificationCodeChecker();

}
