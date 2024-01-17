package net.bodz.lily.criterion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.IStack;

public abstract class Composite
        extends Criterion
        implements
            Iterable<ICriterion> {

    protected List<ICriterion> list = new ArrayList<>();

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
        } else if (in.isObject()) { // field-map
            JsonObject o = in.getObject();
            for (String fieldName : o.keySet()) {
                Object jItemAny = o.get(fieldName);
                JsonVariant jItem = JsonVariant.of(jItemAny);
                JsonObject item = jItem.getObject();

                String discriminator = item.keys().next();
                boolean negated = discriminator.startsWith("~");
                if (negated)
                    discriminator = discriminator.substring(1);

                FieldCriterion fieldCriterion = (FieldCriterion) Criterions.create(discriminator);
                if (fieldCriterion == null)
                    throw new IllegalUsageException("unknown discriminator: " + discriminator);

                fieldCriterion.fieldName = fieldName;

                fieldNameStack.push(fieldName);
                try {
                    fieldCriterion.jsonIn(jItem, true, typeInferrer, fieldNameStack);
                    add(fieldCriterion);
                } finally {
                    fieldNameStack.pop();
                }
            }
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (isFieldMap()) {
            out.object();
            for (ICriterion item : list) {
                FieldCriterion fieldItem = (FieldCriterion) item;
                String key = fieldItem.fieldName;
                if (fieldItem.not)
                    key = "~" + key;
                out.key(key);
                fieldItem.jsonOutDualMode(out, true);
            }
            out.endObject();
        } else {
            out.array();
            for (ICriterion item : list)
                item.jsonOut(out);
            out.endArray();
        }
    }

    public boolean isFieldMap() {
        Set<String> fieldNames = new HashSet<>();
        for (ICriterion item : list) {
            if (!item.isFieldCriterion())
                return false;
            FieldCriterion f = (FieldCriterion) item;
            if (!fieldNames.add(f.fieldName))
                return false;
        }
        return true;
    }

}
