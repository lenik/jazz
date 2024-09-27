package net.bodz.bas.json;

public interface IJsonVisitor<O, A> {

    O object(JsonObject jo);

    A array(JsonArray ja);

    Object simpleValue(Object value);

    Object _null();

}
