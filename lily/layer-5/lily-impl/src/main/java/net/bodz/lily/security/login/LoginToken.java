package net.bodz.lily.security.login;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.typer.std.MutableAttributes;
import net.bodz.lily.security.User;

public class LoginToken
        extends MutableAttributes {

    public static final String ATTRIBUTE_NAME = LoginToken.class.getName();

    static final long MAX_TXN_NO = 0x1000_0000_0000_0000L;

    static final Random RANDOM = new Random();

    long id = System.identityHashCode(this);
    long expireDate;

    private User user;
    private String secret;

    private long transaction;

    public LoginToken(User user, String secret) {
        if (user == null)
            throw new NullPointerException("user");
        if (secret == null)
            throw new NullPointerException("secret");
        this.user = user;
        this.secret = secret;
        this.transaction = next();
    }

    public long getId() {
        return id;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public User getUser() {
        return user;
    }

    long getTransaction() {
        return transaction;
    }

    void setTransaction(long transaction) {
        this.transaction = transaction;
    }

    public long next() {
        transaction = RANDOM.nextLong() % MAX_TXN_NO;
        // keep positive.
        transaction = (transaction + MAX_TXN_NO) % MAX_TXN_NO;
        return transaction;
    }

    public String getExpectedClientResponse() {
        String salt = String.valueOf(transaction);
        String text = salt + secret + salt;
        String hex = DigestUtils.shaHex(text);
        return hex;
    }

    public boolean checkChallengeResponse(String cr) {
        if (cr == null)
            throw new NullPointerException("cr");
        String expected = getExpectedClientResponse().toLowerCase();
        cr = cr.toLowerCase().trim();
        return cr.equals(expected);
    }

    public AjaxResult next(AjaxResult result) {
        result.set("token", id);
        result.set("next", next());
        result.set("timeout", Long.MAX_VALUE);
        return result;
    }

}
