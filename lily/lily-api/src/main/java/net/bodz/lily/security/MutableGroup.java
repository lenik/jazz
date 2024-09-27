package net.bodz.lily.security;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;

public class MutableGroup
        implements
            IMutableGroup {

    Integer id;
    String name;
    String fullName;

    // MutableTypedAttributes attributes = new MutableTypedAttributes();

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public void id(Integer newId) {
        this.id = newId;
    }

    @Override
    public void setUniqName(String uniqName) {
        this.name = uniqName;
    }

    @Override
    public String getName() {
        return name;
    }

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

    public static MutableGroup from(JsonObject o)
            throws ParseException {
        MutableGroup a = new MutableGroup();
        a.jsonIn(o);
        return a;
    }

    private static final String K_ID = "id";
    private static final String K_NAME = "name";
    private static final String K_FULL_NAME = "fullName";

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        id = o.getInt(K_ID);
        name = o.getString(K_NAME);
        fullName = o.getString(K_FULL_NAME);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_ID, id);
        out.entryNotNull(K_NAME, name);
        out.entryNotNull(K_FULL_NAME, fullName);
    }

}
