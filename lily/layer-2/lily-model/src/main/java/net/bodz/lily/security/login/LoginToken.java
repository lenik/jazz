package net.bodz.lily.security.login;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.t.preorder.PrefixMap;
import net.bodz.bas.typer.std.MutableAttributes;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;

public class LoginToken
        extends MutableAttributes
        implements IJsonSerializable {

    public static final String ATTRIBUTE_NAME = LoginToken.class.getName();

    static final long MAX_TXN_NO = 0x1000_0000_0000_0000L;

    static final Random RANDOM = new Random();

    final ILoginTokenManager tokenManager;
    final long id;
    long expireDate;

    final User user;
    final UserSecret secret;

    private long transaction;

    PrefixMap<String> permissions = new PrefixMap<>();

    LoginToken(ILoginTokenManager manager, long id, User user) {
        if (manager == null)
            throw new NullPointerException("manager");
        if (user == null)
            throw new NullPointerException("user");
        this.tokenManager = manager;
        this.id = id;
        this.user = user;
        this.secret = user.getSecret();
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

    public boolean isIgnoreAcl() {
        if (user == null)
            return true;
        if (user.isSuperUser())
            return true;
        return false;
    }

    public PrefixMap<String> getPermissions() {
        return permissions;
    }

    public LoginToken detach() {
        tokenManager.removeToken(id);
        return this;
    }

    /**
     * @return Non-<code>null</code> instance.
     */
    public static LoginToken fromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        else
            return fromSession(session);
    }

    /**
     * @return Non-<code>null</code> instance.
     */
    public static LoginToken fromSession(HttpSession session) {
        return (LoginToken) session.getAttribute(LoginToken.ATTRIBUTE_NAME);
    }

    void saveInSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            throw new IllegalStateException("No session");
        else
            saveInSession(session);
    }

    void saveInSession(HttpSession session) {
        synchronized (session) {
            LoginToken existing = (LoginToken) session.getAttribute(LoginToken.ATTRIBUTE_NAME);
            if (existing != null)
                existing.detach();
            session.setAttribute(LoginToken.ATTRIBUTE_NAME, this);
        }
    }

    void removeFromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null)
            removeFromSession(session);
    }

    void removeFromSession(HttpSession session) {
        LoginToken token = fromSession(session);
        if (token == this)
            clearSession(session);
    }

    static void clearSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null)
            clearSession(session);
    }

    static void clearSession(HttpSession session) {
        synchronized (session) {
            LoginToken token = (LoginToken) session.getAttribute(LoginToken.ATTRIBUTE_NAME);
            if (token != null) {
                token.detach();
                session.removeAttribute(LoginToken.ATTRIBUTE_NAME);
            }
        }
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("id", id);
        out.entry("txn", transaction);
        out.entry("timeout", Long.MAX_VALUE);

        out.entry("user", user.getId());
        out.entry("userName", user.getCodeName());
        out.entry("userFullName", user.getFullName());
    }

}
