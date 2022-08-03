package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    public Object get(Object defaultValue) {
        return null;
    }

    @Override
    public Object getScalar() {
        return null;
    }

    @Override
    public Object getScalar(Object defaultValue) {
        return null;
    }

    @Override
    public Object[] getArray() {
        return null;
    }

    @Override
    public Object[] getArray(Object[] defaultValue) {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public String getString(String defaultString) {
        return null;
    }

    @Override
    public String[] getStringArray() {
        return null;
    }

    @Override
    public String[] getStringArray(String[] defaultValue) {
        return null;
    }

    @Override
    public byte getByte() {
        return 0;
    }

    @Override
    public byte getByte(byte defaultValue) {
        return 0;
    }

    @Override
    public Byte getByte(Byte defaultValue) {
        return null;
    }

    @Override
    public short getShort() {
        return 0;
    }

    @Override
    public short getShort(short defaultValue) {
        return 0;
    }

    @Override
    public Short getShort(Short defaultValue) {
        return null;
    }

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public int getInt(int defaultValue) {
        return 0;
    }

    @Override
    public Integer getInt(Integer defaultValue) {
        return null;
    }

    @Override
    public long getLong() {
        return 0;
    }

    @Override
    public long getLong(long defaultValue) {
        return 0;
    }

    @Override
    public Long getLong(Long defaultValue) {
        return null;
    }

    @Override
    public float getFloat() {
        return 0;
    }

    @Override
    public float getFloat(float defaultValue) {
        return 0;
    }

    @Override
    public Float getFloat(Float defaultValue) {
        return null;
    }

    @Override
    public double getDouble() {
        return 0;
    }

    @Override
    public double getDouble(double defaultValue) {
        return 0;
    }

    @Override
    public Double getDouble(Double defaultValue) {
        return null;
    }

    @Override
    public boolean getBoolean() {
        return false;
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        return false;
    }

    @Override
    public Boolean getBoolean(Boolean defaultValue) {
        return null;
    }

    @Override
    public char getChar() {
        return 0;
    }

    @Override
    public char getChar(char defaultValue) {
        return 0;
    }

    @Override
    public Character getChar(Character defaultValue) {
        return null;
    }

    @Override
    public BigInteger getBigInteger() {
        return null;
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        return null;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public Date getDate(Date defaultValue) {
        return null;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType) {
        return null;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType, T defaultValue) {
        return null;
    }

}
