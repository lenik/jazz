package net.bodz.lily.criterion;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.regex.IPartProcessor;
import net.bodz.bas.c.java.util.regex.TextPreps;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.db.sql.dialect.ISqlDialect;
import net.bodz.bas.db.sql.dialect.SqlDialects;

public class SQLFormatter
        implements
            ICriterionVisitor {

    StringBuilder sb = new StringBuilder();
    ISqlDialect dialect = SqlDialects.POSTGRESQL;

    public SQLFormatter(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
    public void not(Not not) {
        sb.append("not ");
        composite("and", not);
    }

    void composite(String name, Composite composite) {
        switch (composite.size()) {
        case 0:
            // throw new IllegalUsageException();
            sb.append("()");
            return;

        case 1:
            composite.iterator().next().accept(this);
            return;

        default:
            int i = 0;
            sb.append("(");
            for (ICriterion item : composite) {
                if (i++ != 0) {
                    sb.append(' ');
                    sb.append(name);
                    sb.append(' ');
                }
                item.accept(this);
            }
            sb.append(")");
        }
    }

    @Override
    public void junction(Junction junction) {
        composite("and", junction);
    }

    @Override
    public void disjunction(Disjunction junction) {
        composite("or", junction);
    }

    void field(String op, FieldCriterion field, Object value) {
        fieldv(op, field, null, value);
    }

    void fieldv(String op, FieldCriterion field, String delim, Object... values) {
        fieldv(op, field, null, delim, null, values);
    }

    void fieldv(String op, FieldCriterion field, String open, String delim, String close, Object... values) {
        String qName = dialect.qNameSmart(field.fieldName);
        sb.append(qName);

        sb.append(" ");
        sb.append(op);
        sb.append(" ");

        if (open != null)
            sb.append(open);

        int i = 0;
        for (Object val : values) {
            if (i++ != 0)
                sb.append(delim);
            sb.append(toSql(val));
        }

        if (open != null)
            sb.append(close);
    }

    @Override
    public void fieldBetween(FieldBetween<?> fieldBetween) {
        fieldv(fieldBetween.not ? "not between" : "between", //
                fieldBetween, "and", fieldBetween.min, fieldBetween.max);
    }

    @Override
    public void fieldCompare(FieldCompare<?> fieldCompare) {
        CompareMode mode = fieldCompare.mode;
        if (fieldCompare.not)
            mode = mode.not();
        String sqlOp = mode.getSqlOp();
        field(sqlOp, fieldCompare, fieldCompare.value);
    }

    @Override
    public void fieldIn(FieldIn<?> fieldIn) {
        Object[] vals = fieldIn.values.toArray();
        fieldv(fieldIn.not ? "not in" : "in", //
                fieldIn, "(", ", ", ")", vals);
    }

    @Override
    public void fieldLike(FieldLike fieldLike) {
        field(fieldLike.not ? "not like" : "like", //
                fieldLike, fieldLike.pattern);
    }

    @Override
    public void fieldNull(FieldNull fieldNull) {
        field(fieldNull.not ? "is not" : "is", //
                fieldNull, (Object) null);
    }

    @Override
    public void fieldTrue(FieldTrue fieldTrue) {
        String qName = dialect.qNameSmart(fieldTrue.fieldName);
        if (fieldTrue.not)
            sb.append("not ");
        sb.append(qName);
    }

    String toSql(Object value) {
        if (value == null)
            return "null";

        Class<?> type = value.getClass();
        switch (TypeKind.getTypeId(type)) {
        case TypeId.BOOLEAN:
            return ((Boolean) value) ? "true" : "false";

        case TypeId.BYTE:
            return integer(((Byte) value) & 0xFF);
        case TypeId.SHORT:
            return integer(((Short) value) & 0xFFFF);
        case TypeId.INTEGER:
            return integer(((Integer) value));
        case TypeId.LONG:
            return integer(((Long) value));
        case TypeId.BIG_INTEGER:
            return integer(((BigInteger) value));

        case TypeId.FLOAT:
            return decimal(((Float) value));
        case TypeId.DOUBLE:
            return decimal(((Double) value));
        case TypeId.BIG_DECIMAL:
            return decimal(((BigDecimal) value));

        case TypeId.CHARACTER:
            return dialect.qString(value.toString());
        case TypeId.STRING:
            return dialect.qString((String) value);

        case TypeId.DATE:
            return dialect.qDate((Date) value);
        case TypeId.SQL_DATE:
            return dialect.qDate((java.sql.Date) value);

        case TypeId.JODA_DATETIME:
            return dialect.qDateTime((DateTime) value);

        default:
            return dialect.qString(value.toString());
        }
    }

    String quoteName(String composite) {
        String quoted = TextPreps.match("[a-zA-Z_]+", new IPartProcessor() {

            @Override
            public void process(CharSequence in, int start, int end, Appendable out, Matcher matcher)
                    throws IOException {
                String word = matcher.group();
                String quoted = dialect.qNameSmart(word);
                out.append(quoted);
            }

        }).process(composite);
        return quoted;
    }

    String integer(Number n) {
        return n.toString();
    }

    String decimal(Number n) {
        return n.toString();
    }

}
