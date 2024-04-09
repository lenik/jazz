package net.bodz.lily.criterion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import net.bodz.bas.c.java.util.regex.Unescape;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.t.list.IStack;
import net.bodz.bas.t.variant.conv.StringVarConverter;

public class FieldIn<T>
        extends FieldCriterion {

    Class<T> valueType;
    public Collection<T> values;

    public FieldIn(Class<T> valueType) {
        this.valueType = valueType;
        this.values = Collections.emptyList();
    }

    @SafeVarargs
    public FieldIn(String fieldName, boolean yes, Class<T> valueType, T... values) {
        super(fieldName, yes);
        if (valueType == null)
            throw new NullPointerException("valueType");
        if (values == null)
            throw new NullPointerException("values");
        this.valueType = valueType;
        this.values = Arrays.asList(values);
    }

    public FieldIn(String fieldName, boolean yes, Class<T> valueType, Collection<T> values) {
        super(fieldName, yes);
        if (valueType == null)
            throw new NullPointerException("valueType");
        if (values == null)
            throw new NullPointerException("values");
        this.valueType = valueType;
        this.values = values;
    }

    @Override
    public FieldCriterion clone() {
        return new FieldIn<>(fieldName, yes, valueType, values);
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldIn(this);
    }

    @Override
    protected String getDiscriminator() {
        return Criterions.K_IN;
    }

    @Override
    protected void writeValue(IJsonOut out)
            throws IOException, FormatException {
        out.array();
        for (Object item : values) {
            out.value(item);
        }
        out.endArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {
        values.clear();

        this.valueType = (Class<T>) fieldType;

        JsonArray jArray = in.getArrayFor(fieldName);
        for (Object jItemAny : jArray) {
            JsonVariant jItem = JsonVariant.of(jItemAny);
            Object value = jItem.getScalarFor(fieldName);
            T dest = convert(valueType, value);
            values.add(dest);
        }
    }

    boolean isQuoted() {
        switch (TypeKind.getTypeId(valueType)) {
        case TypeId._boolean:
        case TypeId.BOOLEAN:
        case TypeId._byte:
        case TypeId._short:
        case TypeId._int:
        case TypeId._long:
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.FLOAT:
        case TypeId.DOUBLE:
        case TypeId.BIG_INTEGER:
        case TypeId.BIG_DECIMAL:
            return false;
        default:
            return true;
        }
    }

    @Override
    public void parseObject(String s, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        s = s.trim();
        if (! (s.startsWith("(") && s.endsWith(")")))
            throw new ParseException("expect '(' and ')' at start and end");

        s = s.substring(1, s.length() - 1);

        boolean quoted = isQuoted();
        StringTokenizer tokens = new StringTokenizer(s, ", ");
        List<T> list = new ArrayList<>();
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (quoted) {
                if (token.startsWith(StringQuote.doubleQuote) || token.endsWith(StringQuote.doubleQuote))
                    token = token.substring(1, token.length() - 1);
                else if (token.startsWith(StringQuote.singleQuote) || token.endsWith(StringQuote.singleQuote))
                    token = token.substring(1, token.length() - 1);
                else
                    throw new ParseException("token is not quoted: " + token);
                token = token.substring(1, token.length() - 1);
                token = Unescape.unescape(token);
            }
            T val = StringVarConverter.INSTANCE.to(token, valueType);
            list.add(val);
        }

        values.clear();
        values.addAll(list);
    }

    @Override
    public void printObject(ITreeOut out)
            throws IOException {
        boolean quoted = isQuoted();
        out.print("(");
        int i = 0;
        for (T val : values) {
            if (i++ != 0)
                out.print(", ");

            String item;
            if (quoted) {
                String valStr = StringVarConverter.INSTANCE.from(valueType, val);
                item = StringQuote.qqJavaString(valStr);
            } else {
                item = val.toString();
            }

            out.print(item);
        }
        out.print(")");
    }

}
