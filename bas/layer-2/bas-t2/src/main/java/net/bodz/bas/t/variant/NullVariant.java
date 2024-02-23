package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

class NullVariant
        implements
            IVariant {

    @Override
    public String format(Object... args) {
        return null;
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public <T> T get(T defaultValue) {
        return defaultValue;
    }

    @Override
    public Object getScalar() {
        return null;
    }

    @Override
    public Object getScalar(Object defaultValue) {
        return defaultValue;
    }

    @Override
    public Object[] getArray() {
        return null;
    }

    @Override
    public Object[] getArray(Object[] defaultValue) {
        return defaultValue;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public String getString(String defaultValue) {
        return defaultValue;
    }

    @Override
    public String[] getStringArray() {
        return null;
    }

    @Override
    public String[] getStringArray(String[] defaultValue) {
        return defaultValue;
    }

    @Override
    public byte getByte() {
        return 0;
    }

    @Override
    public byte getByte(byte defaultValue) {
        return defaultValue;
    }

    @Override
    public Byte getByte(Byte defaultValue) {
        return defaultValue;
    }

    @Override
    public short getShort() {
        return 0;
    }

    @Override
    public short getShort(short defaultValue) {
        return defaultValue;
    }

    @Override
    public Short getShort(Short defaultValue) {
        return defaultValue;
    }

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public int getInt(int defaultValue) {
        return defaultValue;
    }

    @Override
    public Integer getInt(Integer defaultValue) {
        return defaultValue;
    }

    @Override
    public long getLong() {
        return 0;
    }

    @Override
    public long getLong(long defaultValue) {
        return defaultValue;
    }

    @Override
    public Long getLong(Long defaultValue) {
        return defaultValue;
    }

    @Override
    public float getFloat() {
        return 0;
    }

    @Override
    public float getFloat(float defaultValue) {
        return defaultValue;
    }

    @Override
    public Float getFloat(Float defaultValue) {
        return defaultValue;
    }

    @Override
    public double getDouble() {
        return 0;
    }

    @Override
    public double getDouble(double defaultValue) {
        return defaultValue;
    }

    @Override
    public Double getDouble(Double defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean getBoolean() {
        return false;
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public Boolean getBoolean(Boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public char getChar() {
        return 0;
    }

    @Override
    public char getChar(char defaultValue) {
        return defaultValue;
    }

    @Override
    public Character getChar(Character defaultValue) {
        return defaultValue;
    }

    @Override
    public BigInteger getBigInteger() {
        return null;
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        return defaultValue;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public Date getDate(Date defaultValue) {
        return defaultValue;
    }

    @Override
    public Instant getInstant() {
        return null;
    }

    @Override
    public Instant getInstant(Instant defaultValue) {
        return defaultValue;
    }

    @Override
    public ZonedDateTime getZonedDateTime() {
        return null;
    }

    @Override
    public ZonedDateTime getZonedDateTime(ZonedDateTime defaultValue) {
        return defaultValue;
    }

    @Override
    public OffsetDateTime getOffsetDateTime() {
        return null;
    }

    @Override
    public OffsetDateTime getOffsetDateTime(OffsetDateTime defaultValue) {
        return defaultValue;
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return null;
    }

    @Override
    public LocalDateTime getLocalDateTime(LocalDateTime defaultValue) {
        return defaultValue;
    }

    @Override
    public LocalDate getLocalDate() {
        return null;
    }

    @Override
    public LocalDate getLocalDate(LocalDate defaultValue) {
        return defaultValue;
    }

    @Override
    public LocalTime getLocalTime() {
        return null;
    }

    @Override
    public LocalTime getLocalTime(LocalTime defaultValue) {
        return defaultValue;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType) {
        return null;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType, T defaultValue) {
        return defaultValue;
    }

}
