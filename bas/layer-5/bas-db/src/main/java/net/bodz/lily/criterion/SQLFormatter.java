package net.bodz.lily.criterion;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;

import net.bodz.bas.c.java.util.regex.IPartProcessor;
import net.bodz.bas.c.java.util.regex.TextPreps;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.db.sql.dialect.ISqlDialect;

public class SQLFormatter
        implements
            ICriterionVisitor {

    StringBuilder sb = new StringBuilder();
    ISqlDialect dialect;

    public SQLFormatter(StringBuilder sb, ISqlDialect dialect) {
        this.sb = sb;
        this.dialect = dialect;
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
    public void disjunction(Disjunction disjunction) {
        composite("or", disjunction);
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
        fieldv(fieldBetween.yes ? "between" : "not between", //
                fieldBetween, "and", fieldBetween.min, fieldBetween.max);
    }

    @Override
    public void fieldCompare(FieldCompare<?> fieldCompare) {
        CompareMode mode = fieldCompare.mode;
        if (! fieldCompare.yes)
            mode = mode.not();
        String sqlOp = mode.getSqlOp();
        field(sqlOp, fieldCompare, fieldCompare.value);
    }

    @Override
    public void fieldIn(FieldIn<?> fieldIn) {
        Object[] vals = fieldIn.values.toArray();
        fieldv(fieldIn.yes ? "in" : "not in", //
                fieldIn, "(", ", ", ")", vals);
    }

    @Override
    public void fieldLike(FieldLike fieldLike) {
        field(fieldLike.yes ? "like" : "not like", //
                fieldLike, fieldLike.pattern);
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

//        case TypeId.DATE:
//            return dialect.qDate((Date) value);
//        case TypeId.SQL_DATE:
//            return dialect.qDate((java.sql.Date) value);

//        case TypeId.JODA_DATETIME:
//            return dialect.qDateTime((DateTime) value);

        case TypeId.INSTANT:
            return dialect.qInstant((Instant) value);
        case TypeId.ZONED_DATE_TIME:
            return dialect.qZonedDateTime((ZonedDateTime) value);
        case TypeId.OFFSET_DATE_TIME:
            return dialect.qOffsetDateTime((OffsetDateTime) value);
        case TypeId.LOCAL_DATE_TIME:
            return dialect.qLocalDateTime((LocalDateTime) value);
        case TypeId.LOCAL_DATE:
            return dialect.qLocalDate((LocalDate) value);
        case TypeId.LOCAL_TIME:
            return dialect.qLocalTime((LocalTime) value);

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
