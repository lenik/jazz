package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;

public class FieldNull
        extends FieldCriterion {

    public FieldNull() {
    }

    public FieldNull(String fieldName, boolean not) {
        super(fieldName, not);
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldNull(this);
    }

    @Override
    protected String getDiscriminator() {
        return Criterions.K_NULL;
    }

    @Override
    protected void writeValue(IJsonOut out)
            throws IOException, FormatException {
    }

    @Override
    protected void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {
    }

}
