package net.bodz.lily.criterion;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;

public class CriterionJsonParser {

    boolean fieldMap;
    ITypeInferrer typeInferrer;
    ICompositeCreator defaultComposite = () -> new Junction();

    public ICriterion parse(JsonVariant in)
            throws ParseException {
        return parse(in, new ArrayStack<>());
    }

    public ICriterion parse(JsonVariant in, IStack<String> stack)
            throws ParseException {
        if (in == null)
            throw new NullPointerException("in");
        if (typeInferrer == null)
            throw new NullPointerException("typeInferrer");

        if (in.isArray()) {
            Composite c = defaultComposite.create();
            c.jsonIn(in);
            return c;
        }

        JsonObject o = in.getObject();
        switch (o.keySet().size()) {
        case 0:
            throw new IllegalArgumentException("The json object can't be empty.");
        }

        String firstKey = o.keys().next();
        JsonVariant firstVal = JsonVariant.of(o.get(firstKey));

        boolean negated = firstKey.startsWith("~");
        if (negated)
            firstKey = firstKey.substring(1);

        ICriterion criterion = Criterions.create(firstKey);
        if (criterion != null) {
            if (negated) {
                if (criterion instanceof FieldCriterion) {
                    FieldCriterion f = (FieldCriterion) criterion;
                    f.not = true;
                } else {
                    throw new UnsupportedOperationException();
                }
            }

            criterion.jsonIn(firstVal, typeInferrer, stack);
            return criterion;
        }

        assert negated == false;
        // key[]: field names
        Composite composite = defaultComposite.create();
        composite.jsonIn(in, typeInferrer, stack);
        return composite;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        Boolean fieldMap;
        ITypeInferrer typeInferrer;
        Class<?> context;
        ICompositeCreator defaultComposite;

        public Builder fieldMap(boolean fieldMap) {
            this.fieldMap = fieldMap;
            return this;
        }

        public Builder context(Class<?> context) {
            this.context = context;
            return this;
        }

        public Builder typeInferrer(ITypeInferrer typeInferrer) {
            this.typeInferrer = typeInferrer;
            return this;
        }

        public Builder defaultComposite(ICompositeCreator defaultComposite) {
            this.defaultComposite = defaultComposite;
            return this;
        }

        public CriterionJsonParser build() {
            CriterionJsonParser o = new CriterionJsonParser();
            if (this.fieldMap != null)
                o.fieldMap = this.fieldMap;

            if (this.typeInferrer != null)
                o.typeInferrer = this.typeInferrer;
            else if (this.context != null)
                o.typeInferrer = new FieldTypeInferrer(this.context);

            if (this.defaultComposite != null)
                o.defaultComposite = this.defaultComposite;
            return o;
        }

    }

}
