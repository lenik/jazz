package net.bodz.bas.fmt.json;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.data.codec.builtin.Base64Codec;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.RstFn;
import net.bodz.bas.fmt.rst.RstInput;
import net.bodz.bas.fmt.rst.RstLoader;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.fork.org.json.JSONException;

public class JsonPersistor {

    static final Logger logger = LoggerFactory.getLogger(JsonPersistor.class);

    public static final String KEY_TYPE = "type";
    public static final String KEY_CTYPE = "ctype";
    public static final String KEY_FORM = "form";
    public static final String KEY_VALUE = "value";
    public static final String KEY_LENGTH = "length";

    public static final String FORM_RST = "rst";
    public static final String FORM_JSON = "json";
    public static final String FORM_BASE64 = "base64";

    ClassLoader classLoader;
    Object valueForClassNotFound;
    JsonFormOptions opts = JsonFormOptions.XXX;

    public Object readTyped(String json)
            throws ParseException {
        try {
            JsonObject in = JsonObjectBuilder.getInstance().parse(json);
            return readTyped(in);
        } catch (JSONException e) {
            throw new ParseException(String.format(//
                    "Error parse json: " + e.getMessage()));
        }
    }

    public Object readTyped(JsonObject in)
            throws ParseException {
        Object val = in.get(KEY_VALUE);
        if (val == null)
            return null;

        String typeName = in.getString(KEY_TYPE);
        if ("null".equals(typeName))
            return null;

        Class<?> type;
        try {
            if (classLoader == null)
                type = Class.forName(typeName);
            else
                type = Class.forName(typeName, false, classLoader);
        } catch (ClassNotFoundException e) {
            return valueForClassNotFound;
        }

        if (type.isArray()) {
            JsonArray jsArray = in.getJsonArray(KEY_VALUE);
            int length = jsArray.length();
            String ctypeName = in.getString(KEY_CTYPE);
            Class<?> ctype;
            try {
                if (classLoader == null)
                    ctype = Class.forName(typeName);
                else
                    ctype = Class.forName(typeName, false, classLoader);
            } catch (ClassNotFoundException e) {
                throw new ParseException("Class not found for the component type: " + ctypeName, e);
            }
            Object array = Array.newInstance(ctype, length);
            for (int i = 0; i < length; i++) {
                JsonObject jsItem = jsArray.getJsonObject(i);
                Object item = readTyped(jsItem);
                Array.set(array, i, item);
            }
            return array;
        }

        switch (TypeKind.getTypeId(type)) {
        case TypeId._byte:
        case TypeId.BYTE:
            return in.getByte(KEY_VALUE);

        case TypeId._short:
        case TypeId.SHORT:
            return in.getShort(KEY_VALUE);

        case TypeId._int:
        case TypeId.INTEGER:
            return in.getInt(KEY_VALUE);

        case TypeId._long:
        case TypeId.LONG:
            return in.getLong(KEY_VALUE);

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return in.getBoolean(KEY_VALUE);

        case TypeId._char:
        case TypeId.CHARACTER:
            return in.getChar(KEY_VALUE);

        case TypeId.STRING:
            return in.getString(KEY_VALUE);
        }

        String form = in.getString(KEY_FORM);
        if (form == null)
            form = "unknown";

        if (FORM_BASE64.equals(form)) {
            String base64 = in.getString(KEY_VALUE);
            byte[] serData = Base64Codec.getInstance().decode(base64);
            InputStream serIn = new ByteArrayInputStream(serData);
            Serializable obj;
            try {
                ObjectInputStream oin = new ObjectInputStream(serIn);
                obj = (Serializable) oin.readObject();
            } catch (IOException e) {
                throw new UnexpectedException(e.getMessage(), e);
            } catch (ClassNotFoundException e) {
                return valueForClassNotFound;
            }
            return obj;
        }

        Object obj;
        try {
            obj = type.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new ParseException(String.format(//
                    "Failed to instantiate %s: %s", type, e.getMessage()), e);
        }

        switch (form) {
        case FORM_JSON:
            JsonObject valNode = in.getJsonObject(KEY_VALUE);
            IJsonForm jsobj = (IJsonForm) obj;
            jsobj.jsonIn(valNode, opts);
            return jsobj;

        case FORM_RST:
            String rst = in.getString(KEY_VALUE);
            RstLoader rstLoader = new RstLoader();
            RstInput rstInput = new RstInput(new StringReader(rst));
            IRstForm rstobj;
            try {
                rstobj = (IRstForm) obj;
                rstLoader.load(rstInput, rstobj.getElementHandler());
            } catch (ElementHandlerException e) {
                throw new ParseException(String.format(//
                        "Element handler error: %s", e.getMessage(), e));
            } catch (IOException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
            return rstobj;

        default:
        }

        if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            JsonObject mapNode = in.getJsonObject(KEY_VALUE);
            for (String key : mapNode.keySet()) {
                JsonObject valueNode = mapNode.getJsonObject(key);
                Object value = readTyped(valueNode);
                map.put(key, value);
            }
            return map;
        }

        if (obj instanceof Collection) {
            @SuppressWarnings("unchecked")
            Collection<Object> coll = (Collection<Object>) obj;
            JsonArray arrayNode = in.getJsonArray(KEY_VALUE);
            int n = arrayNode.length();
            for (int i = 0; i < n; i++) {
                JsonObject itemNode = arrayNode.getJsonObject(i);
                Object value = readTyped(itemNode);
                coll.add(value);
            }
            return coll;
        }

        throw new NotImplementedException(String.format(//
                "Unkown persisted type %s, can't restore.", type));
    }

    public void writeNull(IJsonOut out) {
        out.object();
        out.entry(KEY_TYPE, "null");
        out.endObject();
    }

    public void writeTyped(IJsonOut out, Object obj)
            throws IOException, FormatException {
        if (obj == null) {
            writeNull(out);
            return;
        }

        Class<?> type = obj.getClass();
        out.object();
        out.entry(KEY_TYPE, type.getName());
        if (type.isArray())
            out.entry(KEY_CTYPE, type.getComponentType().getName());
        writeNonNullVal(out, obj, opts);
        out.endObject();
    }

    public void writeNonNullVal(IJsonOut out, Object obj, JsonFormOptions opts)
            throws IOException, FormatException {
        Class<?> type = obj.getClass();
        if (type.isArray()) {
            int n = Array.getLength(obj);
            out.entry(KEY_LENGTH, n);
            out.key(KEY_VALUE);
            out.array();
            for (int i = 0; i < n; i++) {
                Object item = Array.get(obj, i);
                writeTyped(out, item);
            }
            out.endArray();
            return;
        }

        switch (TypeKind.getTypeId(type)) {
        case TypeId._byte:
        case TypeId._short:
        case TypeId._int:
        case TypeId._long:
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:
            // out.value(((Number) obj).longValue());
            out.entry(KEY_VALUE, ((Number) obj).longValue());
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            // out.value(((Boolean) obj).booleanValue());
            out.entry(KEY_VALUE, ((Boolean) obj).booleanValue());
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            // out.value(obj.toString());
            out.entry(KEY_VALUE, obj.toString());
            return;
        }

        if (obj instanceof Collection) {
            out.entry(KEY_FORM, "collection");
            out.key(KEY_VALUE);
            out.array();
            Collection<?> coll = (Collection<?>) obj;
            for (Object item : coll)
                writeTyped(out, item);
            out.endArray();
            return;
        }

        if (obj instanceof Map) {
            out.entry(KEY_FORM, "map");
            out.key(KEY_VALUE);
            out.object();
            Map<?, ?> map = (Map<?, ?>) obj;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                out.key(key.toString());
                writeTyped(out, value);
            }
            out.endObject();
            return;
        }

        if (obj instanceof IJsonForm) {
            IJsonForm jsobj = (IJsonForm) obj;
            out.entry(KEY_FORM, FORM_JSON);
            out.key(KEY_VALUE);
            jsobj.jsonOut(out, opts);
            return;
        }

        if (obj instanceof IRstForm) {
            IRstForm rstObj = (IRstForm) obj;
            String rst = RstFn.toString(rstObj);
            out.entry(KEY_FORM, FORM_RST);
            out.key(KEY_VALUE);
            out.value(rst);
            return;
        }

        if (obj instanceof Serializable) {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(buf);
            oout.writeObject(obj);
            byte[] bytes = buf.toByteArray();
            String base64 = Base64Codec.getInstance().encode(bytes);
            out.entry(KEY_FORM, FORM_BASE64);
            out.key(KEY_VALUE);
            out.value(base64);
            return;
        }

        logger.debug("Ignored un-serializable object: " + type + ": " + obj);
    }

}
