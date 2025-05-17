package net.bodz.lily.tool.log;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.site.json.JsonVarMap;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.security.IMutableUser;
import net.bodz.lily.security.IUser;
import net.bodz.lily.security.Users;

public class EventLog
        implements
            Serializable,
            IJsonForm {

    private static final long serialVersionUID = 1L;

    long time;
    IUser user;
    Class<?> entity;
    long id;
    String message;
    Throwable exception;

    public EventLog() {
    }

    public EventLog(IUser user) {
        this.time = System.currentTimeMillis();
        this.user = user;
    }

    public EventLog(IUser user, CoEntity<? extends Number> entity, String message, Throwable exception) {
        this.time = System.currentTimeMillis();
        this.user = user;
        this.entity = entity.getClass();
        this.id = entity.id().longValue();
        this.message = message;
        this.exception = exception;
    }

    public EventLog time(long time) {
        this.time = time;
        return this;
    }

    public EventLog user(IUser user) {
        this.user = user;
        return this;
    }

    public EventLog entity(CoEntity<? extends Number> entity) {
        this.entity = entity.getClass();
        this.id = entity.id().longValue();
        return this;
    }

    public EventLog message(String message) {
        this.message = message;
        return this;
    }

    public EventLog message(String format, Object... args) {
        this.message = String.format(format, args);
        return this;
    }

    public EventLog exception(Throwable exception) {
        this.exception = exception;
        return this;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public IUser getIUser() {
        return user;
    }

    public void setIUser(IUser user) {
        this.user = user;
    }

    public Class<?> getEntity() {
        return entity;
    }

    public void setEntity(Class<?> entity) {
        this.entity = entity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonVarMap map = new JsonVarMap(o);
        time = map.getLong("time", 0);
        message = map.getString("message");

        if (o.has("entity")) {
            JsonObject $entity = o.getJsonObject("entity");
            String type = $entity.getString("type");
            try {
                entity = Class.forName(type);
            } catch (ClassNotFoundException e) {
                throw new ParseException(e.getMessage(), e);
            }
            id = $entity.getLong("id");
        }

        if (o.has("user")) {
            JsonObject $user = o.getJsonObject("user");
            map = new JsonVarMap($user);
            IMutableUser user = Users.newUser();
            user.id(map.getInt("id"));
            user.setName(map.getString("name"));
            user.setFullName(map.getString("fullName"));
            this.user = user;
        }

        // Throwable exception;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("time", time);

        if (entity != null) {
            out.key("entity");
            out.object();
            out.entry("type", entity.getCanonicalName());
            out.entry("id", id);
            out.endObject();
        }

        if (user != null) {
            out.key("user");
            out.object();
            out.entry("id", user.id());
            out.entry("name", user.getName());
            out.entry("fullName", user.getFullName());
            out.endObject();
        }

        if (message != null)
            out.entry("message", message);

        if (exception != null) {
            out.key("exception");
            out.object();
            out.entry("type", exception.getClass().getCanonicalName());
            out.entry("message", exception.getMessage());
            out.endObject();
        }
    }

    @Override
    public String toString() {
        String json;
        try {
            json = JsonFn.toJson(this, JsonFormOptions.COMPACT);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return json;
    }

}
