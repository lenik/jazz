package net.bodz.lily.security.login;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.content.IReset;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.typer.std.MutableAttributes;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginToken
        extends MutableAttributes
        implements
            IReset,
            IJsonForm {

    public static final String ATTRIBUTE_NAME = LoginToken.class.getName();
    public static final String HEADER_NAME = "Login-Token";
    public static final String PARAM_NAME = "loginToken";

    static final long MAX_TXN_NO = 0x1000_0000_0000_0000L;

    static final Random RANDOM = new Random();

    long id;
    long expireDate;

    User user;
    UserSecret secret;

    private long transaction;

    private LoginToken() {
    }

    LoginToken(ILoginTokenManager manager, long id, User user) {
        if (user == null)
            throw new NullPointerException("user");
        this.id = id;
        this.user = user;
        this.secret = user.getSecret();
        this.transaction = next();
    }

    public static LoginToken create() {
        return new LoginToken();
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
        String hex = DigestUtils.sha1Hex(text);
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

//    public PrefixMap<String> getPermissions() {
//        return permissions;
//    }

    public LoginToken detach() {
//        if (tokenManager != null)
//            tokenManager.removeToken(id);
        return this;
    }

    /**
     * @return Non-<code>null</code> instance.
     */
    public static LoginToken fromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        return fromRequest(request);
    }

    public static LoginToken fromRequest(HttpServletRequest request) {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHostOpt();
        if (vhost == null)
            throw new IllegalStateException("no vhost info.");

        ILoginManager loginManager = LoginManagers.requireLoginManager();

        ILoginTokenManager tokenManager = (ILoginTokenManager) loginManager;

        String tokenStr = request.getHeader(HEADER_NAME);
        if (tokenStr == null)
            tokenStr = request.getParameter(PARAM_NAME);
        if (tokenStr != null)
            if (StringPred.isDecimal(tokenStr)) {
                int id = Integer.parseInt(tokenStr);
                LoginToken token = tokenManager.getToken(id);
                return token;
            }

        return fromSession();
    }

    public static LoginToken removeFromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        return removeFromRequest(request);
    }

    public static LoginToken removeFromRequest(HttpServletRequest request) {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHostOpt();
        if (vhost == null)
            throw new IllegalStateException("no vhost info.");

        ILoginManager loginManager = LoginManagers.requireLoginManager();

        ILoginTokenManager tokenManager = (ILoginTokenManager) loginManager;
        LoginToken last = null;

        String tokenStr = request.getHeader(HEADER_NAME);
        if (tokenStr == null)
            tokenStr = request.getParameter(PARAM_NAME);
        if (tokenStr != null)
            if (StringPred.isDecimal(tokenStr)) {
                int id = Integer.parseInt(tokenStr);
                last = tokenManager.removeToken(id);
            }

        LoginToken last2 = removeAnyTokenFromSession();
        return last != null ? last : last2;
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

    LoginToken removeJustThisFromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        return removeJustThisFromSession(session);
    }

    LoginToken removeJustThisFromSession(HttpSession session) {
        LoginToken token = fromSession(session);
        if (token != this)
            return null;
        return removeAnyTokenFromSession(session);
    }

    static LoginToken removeAnyTokenFromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        return removeAnyTokenFromSession(session);
    }

    static LoginToken removeAnyTokenFromSession(HttpSession session) {
        synchronized (session) {
            LoginToken token = (LoginToken) session.getAttribute(LoginToken.ATTRIBUTE_NAME);
            if (token != null) {
                token.detach();
                session.removeAttribute(LoginToken.ATTRIBUTE_NAME);
            }
            return token;
        }
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        int id = o.getInt("id");
        transaction = o.getLong("txn");
        // timeout = o.getLong("timeout");

        JsonObject _user = o.getJsonObject("user");
        if (_user != null) {
            User user = new User();
            user.jsonIn(o, opts);
            this.user = user;
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("id", id);
        out.entry("txn", transaction);
        out.entry("timeout", Long.MAX_VALUE);

        out.key("user");
        out.object();
        {
            out.entry("id", user.id());
            out.entry("name", user.getName());
            out.entry("fullName", user.getFullName());
            out.endObject();
        }
    }

    @Override
    public String toString() {
        String s = String.format("token_%d<user(%d: %s), txn %d>", //
                id, user.id(), user.getUniqName(), transaction);
        return s;
    }

}
