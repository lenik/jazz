package net.bodz.lily.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import net.bodz.lily.criterion.ICriterion;

public abstract class CriteriaBuilderDSL<This>
        implements
            IReceiver<ICriterion> {

    protected String qualify(String fieldName) {
//        ISqlDialect dialect = DataApps.getPreferred().getDataContext().getOptions().getType().getSqlFormat();
//        fieldName = dialect.qNameSmart(fieldName);
        if (fieldName.contains("."))
            return fieldName;
        else
            return "a." + fieldName;
    }

    protected <T extends Number> NumberField<T> number(String fieldName, Class<T> type) {
        return new NumberField<T>(qualify(fieldName), type);
    }

    protected IntegerField integer(String fieldName) {
        return new IntegerField(qualify(fieldName));
    }

    protected ByteField _byte(String fieldName) {
        return new ByteField(qualify(fieldName));
    }

    protected ShortField _short(String fieldName) {
        return new ShortField(qualify(fieldName));
    }

    protected LongField _long(String fieldName) {
        return new LongField(qualify(fieldName));
    }

    protected FloatField _float(String fieldName) {
        return new FloatField(qualify(fieldName));
    }

    protected DoubleField _double(String fieldName) {
        return new DoubleField(qualify(fieldName));
    }

    protected BigIntegerField bigInteger(String fieldName) {
        return new BigIntegerField(qualify(fieldName));
    }

    protected BigDecimalField bigDecimal(String fieldName) {
        return new BigDecimalField(qualify(fieldName));
    }

    protected CharField _char(String fieldName) {
        return new CharField(qualify(fieldName));
    }

    protected StringField string(String fieldName) {
        return new StringField(qualify(fieldName));
    }

    protected BooleanField bool(String fieldName) {
        return new BooleanField(qualify(fieldName));
    }

    protected <T> DateField<T> date(String fieldName, Class<T> type) {
        return new DateField<T>(qualify(fieldName), type);
    }

    protected InstantField instant(String fieldName) {
        return new InstantField(qualify(fieldName));
    }

    protected ZonedDateTimeField zonedDateTime(String fieldName) {
        return new ZonedDateTimeField(qualify(fieldName));
    }

    protected OffsetDateTimeField offsetDateTime(String fieldName) {
        return new OffsetDateTimeField(qualify(fieldName));
    }

    protected LocalDateTimeField localDateTime(String fieldName) {
        return new LocalDateTimeField(qualify(fieldName));
    }

    protected LocalDateField localDate(String fieldName) {
        return new LocalDateField(qualify(fieldName));
    }

    protected LocalTimeField localTime(String fieldName) {
        return new LocalTimeField(qualify(fieldName));
    }

    public class NumberField<T extends Number>
            extends NumberFieldCriterionBuilder<This, T> {

        @SuppressWarnings("unchecked")
        public NumberField(String fieldName, Class<T> type) {
            super(fieldName, type, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class IntegerField
            extends NumberField<Integer> {

        public IntegerField(String fieldName) {
            super(fieldName, Integer.class);
        }

    }

    public class ByteField
            extends NumberField<Byte> {

        public ByteField(String fieldName) {
            super(fieldName, Byte.class);
        }

    }

    public class ShortField
            extends NumberField<Short> {

        public ShortField(String fieldName) {
            super(fieldName, Short.class);
        }

    }

    public class LongField
            extends NumberField<Long> {

        public LongField(String fieldName) {
            super(fieldName, Long.class);
        }

    }

    public class FloatField
            extends NumberField<Float> {

        public FloatField(String fieldName) {
            super(fieldName, Float.class);
        }

    }

    public class DoubleField
            extends NumberField<Double> {

        public DoubleField(String fieldName) {
            super(fieldName, Double.class);
        }

    }

    public class BigIntegerField
            extends NumberField<BigInteger> {

        public BigIntegerField(String fieldName) {
            super(fieldName, BigInteger.class);
        }

    }

    public class BigDecimalField
            extends NumberField<BigDecimal> {

        public BigDecimalField(String fieldName) {
            super(fieldName, BigDecimal.class);
        }

    }

    public class CharField
            extends CharFieldCriterionBuilder<This> {

        @SuppressWarnings("unchecked")
        public CharField(String fieldName) {
            super(fieldName, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class StringField
            extends StringFieldCriterionBuilder<This> {

        @SuppressWarnings("unchecked")
        public StringField(String fieldName) {
            super(fieldName, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class BooleanField
            extends BooleanFieldCriterionBuilder<This> {

        @SuppressWarnings("unchecked")
        public BooleanField(String fieldName) {
            super(fieldName, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class DateField<T>
            extends DateFieldCriterionBuilder<This, T> {

        @SuppressWarnings("unchecked")
        public DateField(String fieldName, Class<T> type) {
            super(fieldName, type, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class InstantField
            extends DateField<Instant> {

        public InstantField(String fieldName) {
            super(fieldName, Instant.class);
        }

    }

    public class ZonedDateTimeField
            extends DateField<ZonedDateTime> {

        public ZonedDateTimeField(String fieldName) {
            super(fieldName, ZonedDateTime.class);
        }

    }

    public class OffsetDateTimeField
            extends DateField<OffsetDateTime> {

        public OffsetDateTimeField(String fieldName) {
            super(fieldName, OffsetDateTime.class);
        }

    }

    public class LocalDateTimeField
            extends DateField<LocalDateTime> {

        public LocalDateTimeField(String fieldName) {
            super(fieldName, LocalDateTime.class);
        }

    }

    public class LocalDateField
            extends DateField<LocalDate> {

        public LocalDateField(String fieldName) {
            super(fieldName, LocalDate.class);
        }

    }

    public class LocalTimeField
            extends DateField<LocalTime> {

        public LocalTimeField(String fieldName) {
            super(fieldName, LocalTime.class);
        }

    }

    protected <T extends Number> DualNumberField<T> dualNumber(String fieldName1, String fieldName2, Class<T> type) {
        return new DualNumberField<T>(fieldName1, fieldName2, type);
    }

    protected DualIntegerField dualInteger(String fieldName1, String fieldName2) {
        return new DualIntegerField(fieldName1, fieldName2);
    }

    protected DualByteField dualByte(String fieldName1, String fieldName2) {
        return new DualByteField(fieldName1, fieldName2);
    }

    protected DualShortField dualShort(String fieldName1, String fieldName2) {
        return new DualShortField(fieldName1, fieldName2);
    }

    protected DualLongField dualLong(String fieldName1, String fieldName2) {
        return new DualLongField(fieldName1, fieldName2);
    }

    protected DualFloatField dualFloat(String fieldName1, String fieldName2) {
        return new DualFloatField(fieldName1, fieldName2);
    }

    protected DualDoubleField dualDouble(String fieldName1, String fieldName2) {
        return new DualDoubleField(fieldName1, fieldName2);
    }

    protected DualBigIntegerField dualBigInteger(String fieldName1, String fieldName2) {
        return new DualBigIntegerField(fieldName1, fieldName2);
    }

    protected DualBigDecimalField dualBigDecimal(String fieldName1, String fieldName2) {
        return new DualBigDecimalField(fieldName1, fieldName2);
    }

    protected DualStringField dualString(String fieldName1, String fieldName2) {
        return new DualStringField(fieldName1, fieldName2);
    }

    protected DualInstantField dualInstant(String fieldName1, String fieldName2) {
        return new DualInstantField(fieldName1, fieldName2);
    }

    protected DualZonedDateTimeField dualZonedDateTime(String fieldName1, String fieldName2) {
        return new DualZonedDateTimeField(fieldName1, fieldName2);
    }

    protected DualOffsetDateTimeField dualOffsetDateTime(String fieldName1, String fieldName2) {
        return new DualOffsetDateTimeField(fieldName1, fieldName2);
    }

    protected DualLocalDateTimeField dualLocalDateTime(String fieldName1, String fieldName2) {
        return new DualLocalDateTimeField(fieldName1, fieldName2);
    }

    protected DualLocalDateField dualLocalDate(String fieldName1, String fieldName2) {
        return new DualLocalDateField(fieldName1, fieldName2);
    }

    protected DualLocalTimeField dualLocalTime(String fieldName1, String fieldName2) {
        return new DualLocalTimeField(fieldName1, fieldName2);
    }

    protected <T> DualDateField<T> dualDate(String fieldName1, String fieldName2, Class<T> type) {
        return new DualDateField<T>(fieldName1, fieldName2, type);
    }

    public class DualNumberField<T>
            extends DualFieldCriterionBuilder<This, DualNumberField<T>, T> {

        @SuppressWarnings("unchecked")
        public DualNumberField(String fieldName1, String fieldName2, Class<T> type) {
            super(fieldName1, fieldName2, type, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class DualIntegerField
            extends DualNumberField<Integer> {

        public DualIntegerField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Integer.class);
        }

    }

    public class DualByteField
            extends DualNumberField<Byte> {

        public DualByteField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Byte.class);
        }

    }

    public class DualShortField
            extends DualNumberField<Short> {

        public DualShortField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Short.class);
        }

    }

    public class DualLongField
            extends DualNumberField<Long> {

        public DualLongField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Long.class);
        }

    }

    public class DualFloatField
            extends DualNumberField<Float> {

        public DualFloatField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Float.class);
        }

    }

    public class DualDoubleField
            extends DualNumberField<Double> {

        public DualDoubleField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Double.class);
        }

    }

    public class DualBigIntegerField
            extends DualNumberField<BigInteger> {

        public DualBigIntegerField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, BigInteger.class);
        }

    }

    public class DualBigDecimalField
            extends DualNumberField<BigDecimal> {

        public DualBigDecimalField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, BigDecimal.class);
        }

    }

    public class DualStringField
            extends DualFieldCriterionBuilder<This, DualStringField, String> {

        @SuppressWarnings("unchecked")
        public DualStringField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, String.class, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class DualDateField<T>
            extends DualFieldCriterionBuilder<This, DualDateField<T>, T> {

        @SuppressWarnings("unchecked")
        public DualDateField(String fieldName1, String fieldName2, Class<T> type) {
            super(fieldName1, fieldName2, type, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    public class DualInstantField
            extends DualDateField<Instant> {

        public DualInstantField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, Instant.class);
        }

    }

    public class DualZonedDateTimeField
            extends DualDateField<ZonedDateTime> {

        public DualZonedDateTimeField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, ZonedDateTime.class);
        }

    }

    public class DualOffsetDateTimeField
            extends DualDateField<OffsetDateTime> {

        public DualOffsetDateTimeField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, OffsetDateTime.class);
        }

    }

    public class DualLocalDateTimeField
            extends DualDateField<LocalDateTime> {

        public DualLocalDateTimeField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, LocalDateTime.class);
        }

    }

    public class DualLocalDateField
            extends DualDateField<LocalDate> {

        public DualLocalDateField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, LocalDate.class);
        }

    }

    public class DualLocalTimeField
            extends DualDateField<LocalTime> {

        public DualLocalTimeField(String fieldName1, String fieldName2) {
            super(fieldName1, fieldName2, LocalTime.class);
        }

    }

    public class DiscreteField<T>
            extends DiscreteFieldCriterionBuilder<This, DiscreteField<T>, T> {

        @SuppressWarnings("unchecked")
        public DiscreteField(String fieldName, Class<T> type) {
            super(fieldName, type, (This) CriteriaBuilderDSL.this, CriteriaBuilderDSL.this);
        }

    }

    protected <T> DiscreteField<T> discrete(String fieldName, Class<T> type) {
        return new DiscreteField<T>(fieldName, type);
    }

}
