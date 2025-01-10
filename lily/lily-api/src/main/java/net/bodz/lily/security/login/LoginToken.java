package net.bodz.lily.security.login;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.content.IReset;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.rtx.MutableAttributes;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.lily.security.IUser;
import net.bodz.lily.security.IUserSecret;
import net.bodz.lily.security.Users;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.ILoginTokenManager;

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
    long expireDate = 0;
    boolean logging;

    AuthData authData;
//    IUserSecret secret;

    private long transaction;

    LoginToken(AuthData authData) {
        if (authData == null)
            throw new NullPointerException("authUser");
        this.authData = authData;
        this.transaction = next();
    }

    private LoginToken() {
    }

    public static LoginToken fromJson(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        LoginToken token = new LoginToken();
        token.jsonIn(o);
        return token;
    }

    public long getId() {
        return id;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public boolean isTransparent() {
        return ! isLogging();
    }

    public boolean isLogging() {
        return logging;
    }

    public IUser getUser() {
        return authData.getUser();
    }

    public long getTransaction() {
        return transaction;
    }

    void setTransaction(long transaction) {
        this.transaction = transaction;
    }

    public long next() {
        transaction = RANDOM.nextLong(MAX_TXN_NO);
        return transaction;
    }

    IUserSecret getSecret() {
        IUserSecret secret = authData.getUser().getSecret();
        return secret;
    }

    public String getExpectedClientResponse() {
        String salt = String.valueOf(transaction);

        IUserSecret secret = getSecret();
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
        IUser user = authData.getUser();
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

    public void saveInSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            throw new IllegalStateException("No session");
        else
            saveInSession(session);
    }

    public void saveInSession(HttpSession session) {
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
            IUser user = Users.newUser();
            user.jsonIn(o, opts);
            Object authId = user.getName();
            authData = AuthData.complete(this, authId, user);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("type", LoginToken.class.getSimpleName());
        // out.entry("authority", authData.getAuthority().getClass().getSimpleName());
        out.entry("id", id);
        out.entry("txn", transaction);
        out.entry("timeout", Long.MAX_VALUE);

        authData.jsonOut(out, opts);
    }

    @Override
    public String toString() {
        IUser user = authData.getUser();
        String s = String.format("token_%d<user(%d: %s), txn %d>", //
                id, user.id(), user.getName(), transaction);
        return s;
    }

}
