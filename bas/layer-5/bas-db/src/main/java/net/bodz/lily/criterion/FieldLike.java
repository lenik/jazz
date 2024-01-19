package net.bodz.lily.criterion;

import java.io.BufferedReader;
import java.io.IOException;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.ITreeOut;

public class FieldLike
        extends FieldCriterion {

    public String pattern;

    public FieldLike() {
    }

    public FieldLike(String fieldName, boolean yes, String pattern) {
        super(fieldName, yes);
        this.pattern = pattern;
    }

    @Override
    public FieldCriterion clone() {
        return new FieldLike(pattern, yes, pattern);
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
    public void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {
        pattern = (String) in.getScalarFor(fieldName);
    }

    @Override
    public void parseObject(BufferedReader in)
            throws ParseException, IOException {
    }

    @Override
    public void printObject(ITreeOut out)
            throws IOException {
        String qPattern = StringQuote.qqJavaString(pattern);
        out.print(qPattern);
    }

}
