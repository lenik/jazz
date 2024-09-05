package net.bodz.lily.security.auth;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.security.IUser;
import net.bodz.lily.security.IUserSecret;

public class AuthData
        implements
            IJsonForm {

    ZonedDateTime authTime = ZonedDateTime.now();
    ZonedDateTime expireTime;

    Object authority;
    String method;
    Object authId;

    IUser user;
    final Map<Class<?>, Object> items = new LinkedHashMap<>();

    FlyingIndex flyingIndex;

    boolean completed;
    // String stateName;
//    JsonResult result = new JsonResult();

    public AuthData(Object authority, Object id, boolean completed) {
        this.authority = authority;
        this.authId = id;
        this.completed = completed;
    }

//    public static AuthData auth(Object authority, IUser user) {
//        return new AuthData(authority).user(user);
//    }
    public static AuthData complete(Object authority, Object id, IUser user) {
        return new AuthData(authority, id, true).user(user);
    }

    public static AuthData pending(Object authority) {
        return new AuthData(authority, null, false);
    }

    public ZonedDateTime getAuthTime() {
        return authTime;
    }

    public void setAuthTime(ZonedDateTime authTime) {
        this.authTime = authTime;
    }

    public ZonedDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(ZonedDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Object getAuthority() {
        return authority;
    }

    public void setAuthority(Object authority) {
        this.authority = authority;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getAuthId() {
        return authId;
    }

    public void setAuthId(Object authId) {
        this.authId = authId;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public AuthData user(IUser user) {
        if (user == null)
            throw new NullPointerException("user");
        this.user = user;
        return this;
    }

    public IUserSecret getSecret() {
        return user.getSecret();
    }

    public Map<Class<?>, Object> getItems() {
        return items;
    }

    public <E> AuthData item(E item) {
        @SuppressWarnings("unchecked")
        Class<? super E> clazz = (Class<? super E>) item.getClass();
        return item(clazz, item);
    }

    public <E> AuthData item(Class<? super E> clazz, E item) {
        if (item == null)
            throw new NullPointerException("item");
        items.put(clazz, item);
        return this;
    }

    public AuthData flyingIndex(FlyingIndex fIndex) {
        this.flyingIndex = fIndex;
        return this;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

//    public JsonResult getResult() {
//        return result;
//    }
//
//    public void setResult(JsonResult result) {
//        this.result = result;
//    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (user != null) {
            sb.append("user: " + user);
        }

        if (authority != null) {
            sb.append("authority class: " + authority.getClass().getName() + "\n");
            sb.append("authority: " + authority);
        }

        if (! items.isEmpty()) {
            sb.append("items: ");
            for (Class<?> clazz : items.keySet()) {
                Object item = items.get(clazz);
                sb.append("    " + clazz.getName() + ": " + item + "\n");
            }
        }
        return sb.toString();
    }

}
