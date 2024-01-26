package net.bodz.lily.criterion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.IStack;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public abstract class Composite
        extends Criterion
        implements
            Iterable<ICriterion>,
            IVarMapForm {

    protected List<ICriterion> list = new ArrayList<>();

    @Override
    public ICriterion reduce() {
        List<ICriterion> rlist = new ArrayList<>();
        for (ICriterion item : list) {
            ICriterion reduced = item.reduce();
            if (reduced != null)
                rlist.add(reduced);
        }
        list = rlist;

        switch (list.size()) {
        case 0:
            return null;
        case 1:
            return list.get(0);
        }
        return this;
    }

    @Override
    public void jsonIn(JsonVariant in, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        list.clear();
        if (in.isArray()) {
            JsonArray jArray = in.getArray();
            for (Object jItemAny : jArray) {
                JsonVariant jItem = JsonVariant.of(jItemAny);
                CriterionJsonParser jsonParser = CriterionJsonParser.builder().typeInferrer(typeInferrer).build();
                ICriterion criterion = jsonParser.parse(jItem, fieldNameStack);
                add(criterion);
            }
            return;
        }

        if (! in.isObject())
            throw new ParseException("composite should be array or object.");

        JsonObject o = in.getObject();

        // field-map
        for (String fieldName : o.keySet()) {
            Object jAnyDef = o.get(fieldName);
            JsonVariant jFieldDef = JsonVariant.of(jAnyDef);

            boolean yes = ! fieldName.startsWith("~");
            if (! yes)
                fieldName = fieldName.substring(1);
            fieldNameStack.push(fieldName);

            try {
                switch (jFieldDef.getType()) {
                case SCALAR: // field-equals
                    Class<?> fieldType = typeInferrer.getFieldType(fieldNameStack);

                    FieldCompare<Object> fieldCompare = new FieldCompare<>(fieldName, yes, CompareMode.EQUALS, null);
                    fieldCompare.readValue(jFieldDef, fieldType);
                    add(fieldCompare);
                    break;

                case OBJECT:
                    JsonObject item = jFieldDef.getObject();

                    String discriminator = item.keys().next();
                    boolean negated = discriminator.startsWith("~");
                    if (negated)
                        discriminator = discriminator.substring(1);

                    FieldCriterion fieldCriterion = (FieldCriterion) Criterions.create(discriminator);
                    if (fieldCriterion == null)
                        throw new IllegalUsageException("unknown discriminator: " + discriminator);

                    fieldCriterion.fieldName = fieldName;

                    fieldCriterion.jsonIn(jFieldDef, true, typeInferrer, fieldNameStack);
                    add(fieldCriterion);
                    break;

                default:
                    throw new ParseException("unexpected field-def type: " + jFieldDef);
                } // switch

            } finally {
                fieldNameStack.pop();
            }
        } // for field
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        boolean mixed = size() > 1 && ! canBeFieldMap();

        if (mixed)
            out.array();

        char state = 'V';

        for (ICriterion item : list) {
            if (item.isFieldCriterion()) {
                if (state == 'V') {
                    out.object();
                    state = 'X';
                }
                FieldCriterion fieldItem = (FieldCriterion) item;
                String key = fieldItem.fieldName;
                if (fieldItem.isFieldEquals() && ! fieldItem.isEqualsOrNot())
                    key = "~" + key;
                out.key(key);
                fieldItem.jsonOutSmart(out, true/* useFieldMap */);
            } else {
                if (state == 'X') {
                    out.endObject();
                    state = 'V';
                }
                item.jsonOutValue(out, opts);
            }
        }

        if (state == 'X')
            out.endObject();

        if (mixed)
            out.endArray();
    }

    public boolean canBeFieldMap() {
        Set<String> fieldNames = new HashSet<>();
        for (ICriterion item : list) {
            if (! item.isFieldCriterion())
                return false;
            FieldCriterion f = (FieldCriterion) item;
            if (! fieldNames.add(f.fieldName))
                // a json object can't have duplicate keys (fieldNames).
                // so use json array instead.
                return false;
        }
        return true;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public ICriterion getLast() {
        int n = list.size();
        return list.get(n - 1);
    }

    ICriterion get(int index) {
        return list.get(index);
    }

    @Override
    public Iterator<ICriterion> iterator() {
        return list.iterator();
    }

    public boolean add(ICriterion e) {
        return list.add(e);
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public void clear() {
        list.clear();
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        for (String key : map.keySet()) {
            String val = map.getString(key);
            boolean negate = key.startsWith("~");
            if (negate)
                key = key.substring(1);

            int colon = key.indexOf(':');
            String opName = null;
            if (colon == -1) // :eq is required in query string.
                continue;

            opName = key.substring(colon + 1);
            key = key.substring(0, colon);

            if (opName.startsWith("~")) {
                negate = ! negate;
                opName = opName.substring(1);
            }

            ICriterion criterion = Criterions.create(opName);
            if (criterion == null) // skip unknown op.
                continue;

            BufferedReader in = new BufferedReader(new StringReader(val));
            try {
                criterion.parseObject(in);
            } catch (Exception e) {
                throw new ParseException("failed to parse " + key + ": " + e.getMessage(), e);
            }

            add(criterion);
        }
    }

    @Override
    public void parseObject(BufferedReader in)
            throws ParseException, IOException {
    }

    @Override
    public void printObject(ITreeOut out)
            throws IOException {
        StringBuilder buf = new StringBuilder();
        accept(new LispFormatter(buf));
        String lisp = buf.toString();
        out.print(lisp);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

}
