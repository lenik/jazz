package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;

public class FieldLike
        extends FieldCriterion {

    public String pattern;

    public FieldLike() {
    }

    public FieldLike(String fieldName, boolean not, String pattern) {
        super(fieldName, not);
        this.pattern = pattern;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldLike(this);
    }

    @Override
    protected String getDiscriminator() {
        return Criterions.K_LIKE;
    }

    @Override
    protected void writeValue(IJsonOut out)
            throws IOException, FormatException {
        out.value(pattern);
    }

    @Override
    protected void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {
        pattern = (String) in.getScalarFor(fieldName);
    }

}
