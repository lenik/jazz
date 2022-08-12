package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.AbstractRstHandler;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.json.JsonObject;

public class JsonPersistorTest
        extends Assert {

    JsonPersistor persistor = new JsonPersistor();
    JsonWriter out = JsonWriter.buffer();

    @Before
    public void prepare() {
        // out = JsonWriter.buffer();
    }

    @Test
    public void testMap()
            throws Exception {
        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("name", "Lucy");
        obj.put("age", 13);
        obj.put("sex", "female");
        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("map", json);

        Object back = persistor.readTyped(json);
        assertEquals(obj, back);
    }

    @Test
    public void testList()
            throws Exception {
        List<Object> obj = new ArrayList<>();
        obj.add("Lucy");
        obj.add(13);
        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("list", json);

        Object back = persistor.readTyped(json);
        assertEquals(obj, back);
    }

    void populate(IFoo foo) {
        foo.setName("Lucy");
        foo.setAge(13);
    }

    @Test
    public void testJsonSerializable()
            throws Exception {
        Foo obj = new JsonFoo();
        populate(obj);
        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("json", json);
        Object back = persistor.readTyped(json);
        assertEquals(obj, back);
    }

    @Test
    public void testRstSerializable()
            throws Exception {
        Foo obj = new RstFoo();
        populate(obj);
        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("rst", json);
        Object back = persistor.readTyped(json);
        assertEquals(obj, back);
    }

    @Test
    public void testSerializable()
            throws Exception {
        IFoo obj = new SerializableFoo();
        populate(obj);
        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("serializable", json);
        Object back = persistor.readTyped(json);
        assertEquals(obj, back);
    }

    public void testNonSerializable()
            throws Exception {
        Foo obj = new Foo();
        populate(obj);
        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("non-serializable", json);
        Object back = persistor.readTyped(json);
        assertNull(back);
    }

    @Test(expected = NotImplementedException.class)
    public void testNonSerializable2()
            throws IOException, ParseException {
        Foo obj = new Foo();
        populate(obj);
        // persistor.writeTyped(out, obj);
        {
            out.object();
            out.entry(JsonPersistor.KEY_TYPE, obj.getClass().getName());
            out.entry(JsonPersistor.KEY_VALUE, "unknown format");
            out.endObject();
        }
        String json = out.toString();
        dump("non-serializable", json);
        Object back = persistor.readTyped(json);
        assertEquals(obj, back); // shouldn't run here.
    }

    @Test
    public void testMixed()
            throws Exception {
        JsonFoo foo = new JsonFoo();
        populate(foo);

        BarMixed obj = new BarMixed();
        obj.color = "pink";
        obj.foo = foo;

        persistor.writeTyped(out, obj);
        String json = out.toString();
        dump("mixed", json);
        Object back = persistor.readTyped(json);
        assertEquals(obj, back);
    }

    static void dump(String header, String json) {
        System.out.println("--------------------- " + header + " ---------------------");
        System.out.println(json);
        System.out.println();
    }

    public static void main(String[] args) {

    }

}

interface IFoo {

    String getName();

    void setName(String name);

    int getAge();

    void setAge(int age);

}

abstract class BaseFoo
        implements
            IFoo {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getAge();
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IFoo o = (IFoo) obj;
        if (!Nullables.equals(getName(), o.getName()))
            return false;
        if (getAge() != o.getAge())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("name %s, age %d", getName(), getAge());
    }

}

class Foo
        extends BaseFoo
        implements
            IFoo {

    String name;
    int age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

}

class SerializableFoo
        extends BaseFoo
        implements
            IFoo,
            Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    int age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

}

class JsonFoo
        extends Foo
        implements
            IJsonForm {

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        name = o.getString("name");
        age = o.getInt("age");
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.object();
        out.entry("name", name);
        out.entry("age", age);
        out.endObject();
    }

}

class RstFoo
        extends Foo
        implements
            IRstForm {

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        out.attribute("name", name);
        out.attribute("age", age);
    }

    @Override
    public IRstHandler getElementHandler() {
        return new EH();
    }

    class EH
            extends AbstractRstHandler {

        @Override
        public boolean attribute(String name, String data)
                throws ParseException, ElementHandlerException {
            switch (name) {
            case "name":
                RstFoo.this.name = StringEscape.parseQuotedJavaString(data);
                return true;
            case "age":
                age = Integer.parseInt(data);
                return true;
            }
            return false;
        }

    }

}

class BarMixed
        implements
            IJsonForm {

    String color;
    JsonFoo foo;

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        color = o.getString("color");
        foo = o.readInto("foo", new JsonFoo());
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.object();
        out.entry("color", color);
        out.key("foo");
        foo.jsonOut(out, opts);
        out.endObject();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((foo == null) ? 0 : foo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BarMixed other = (BarMixed) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (foo == null) {
            if (other.foo != null)
                return false;
        } else if (!foo.equals(other.foo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("color=%s, foo=(%s)", color, foo);
    }

}