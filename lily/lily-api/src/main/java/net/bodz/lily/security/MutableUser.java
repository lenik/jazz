package net.bodz.lily.security;

import java.io.IOException;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjects;
import net.bodz.bas.typer.std.MutableTypedAttributes;
import net.bodz.lily.entity.IId;

public class MutableUser
        implements
            IMutableUser,
            IId<Integer> {

    Integer id;
    String name;
    String fullName;
    boolean superUser;
    IGroup primaryGroup;
    IUserSecret secret;

    MutableTypedAttributes attributes = new MutableTypedAttributes();

    @Override
    public Class<Integer> idType() {
        return Integer.class;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public void id(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean isSuperUser() {
        return superUser;
    }

    @Override
    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    @Override
    public IGroup getPrimaryGroup() {
        return primaryGroup;
    }

    @Override
    public void setPrimaryGroup(IGroup primaryGroup) {
        this.primaryGroup = primaryGroup;
    }

    @Override
    public IUserSecret getSecret() {
        return secret;
    }

    public void setSecret(IUserSecret secret) {
        this.secret = secret;
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.getAttributeNames();
    }

    @Override
    public boolean isAttributePresent(String name) {
        return attributes.isAttributePresent(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return attributes.getAttribute(name, defaultValue);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.setAttribute(name, value);
    }

    public static MutableUser from(JsonObject o)
            throws ParseException {
        MutableUser user = new MutableUser();
        user.jsonIn(o);
        return user;
    }

    private static final String K_ID = "id";
    private static final String K_NAME = "name";
    private static final String K_FULL_NAME = "fullName";
    private static final String K_SUPER_USER = "superUser";
    private static final String K_PRIMARY_GROUP = "primaryGroup";
    private static final String K_ATTRIBUTES = "attributes";

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        id = o.getInt(K_ID);
        name = o.getString(K_NAME);
        fullName = o.getString(K_FULL_NAME);
        superUser = o.getBoolean(K_SUPER_USER, superUser);
        if (o.containsKey(K_PRIMARY_GROUP)) {
            primaryGroup = MutableGroup.from(o.getJsonObject(K_PRIMARY_GROUP));
        }
        if (o.containsKey(K_ATTRIBUTES))
            attributes = JsonObjects.parseAttributes(o.getJsonObject(K_ATTRIBUTES));
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_ID, id);
        out.entryNotNull(K_NAME, name);
        out.entryNotNull(K_FULL_NAME, fullName);
        out.entryTrue(K_SUPER_USER, superUser);
        out.entryNotNull(K_PRIMARY_GROUP, primaryGroup);
        out.entryNotNull(K_ATTRIBUTES, attributes);
    }

}
