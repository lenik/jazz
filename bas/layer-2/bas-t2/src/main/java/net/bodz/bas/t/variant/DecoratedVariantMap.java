package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedVariantMap<K>
        extends AbstractDecorator<IVariantMap<K>>
        implements IVariantMap<K> {

    private static final long serialVersionUID = 1L;

    public DecoratedVariantMap(IVariantMap<K> _orig) {
        super(_orig);
    }

    @Override
    public Set<K> keySet() {
        return getWrapped().keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return getWrapped().containsKey(key);
    }

    @Override
    public String format(K formatKey, Object... args) {
        return getWrapped().format(formatKey, args);
    }

    @Override
    public Object get(Object key) {
        return getWrapped().get(key);
    }

    @Override
    public Object get(Object key, Object defaultValue) {
        return getWrapped().get(key, defaultValue);
    }

    @Override
    public Object getScalar(K key) {
        return getWrapped().getScalar(key);
    }

    @Override
    public Object getScalar(K key, Object defaultValue) {
        return getWrapped().getScalar(key, defaultValue);
    }

    @Override
    public Object[] getArray(K key) {
        return getWrapped().getArray(key);
    }

    @Override
    public Object[] getArray(K key, Object[] defaultValue) {
        return getWrapped().getArray(key, defaultValue);
    }

    @Override
    public String getString(K key) {
        return getWrapped().getString(key);
    }

    @Override
    public String getString(K key, String defaultString) {
        return getWrapped().getString(key, defaultString);
    }

    @Override
    public String[] getStringArray(K key) {
        return getWrapped().getStringArray(key);
    }

    @Override
    public String[] getStringArray(K key, String[] defaultValue) {
        return getWrapped().getStringArray(key, defaultValue);
    }

    @Override
    public Byte getByte(K key) {
        return getWrapped().getByte(key);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        return getWrapped().getByte(key, defaultValue);
    }

    @Override
    public Byte getByte(K key, Byte defaultValue) {
        return getWrapped().getByte(key, defaultValue);
    }

    @Override
    public Short getShort(K key) {
        return getWrapped().getShort(key);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        return getWrapped().getShort(key, defaultValue);
    }

    @Override
    public Short getShort(K key, Short defaultValue) {
        return getWrapped().getShort(key, defaultValue);
    }

    @Override
    public Integer getInt(K key) {
        return getWrapped().getInt(key);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        return getWrapped().getInt(key, defaultValue);
    }

    @Override
    public Integer getInt(K key, Integer defaultValue) {
        return getWrapped().getInt(key, defaultValue);
    }

    @Override
    public Long getLong(K key) {
        return getWrapped().getLong(key);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        return getWrapped().getLong(key, defaultValue);
    }

    @Override
    public Long getLong(K key, Long defaultValue) {
        return getWrapped().getLong(key, defaultValue);
    }

    @Override
    public Float getFloat(K key) {
        return getWrapped().getFloat(key);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        return getWrapped().getFloat(key, defaultValue);
    }

    @Override
    public Float getFloat(K key, Float defaultValue) {
        return getWrapped().getFloat(key, defaultValue);
    }

    @Override
    public Double getDouble(K key) {
        return getWrapped().getDouble(key);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        return getWrapped().getDouble(key, defaultValue);
    }

    @Override
    public Double getDouble(K key, Double defaultValue) {
        return getWrapped().getDouble(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(K key) {
        return getWrapped().getBoolean(key);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        return getWrapped().getBoolean(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(K key, Boolean defaultValue) {
        return getWrapped().getBoolean(key, defaultValue);
    }

    @Override
    public Character getChar(K key) {
        return getWrapped().getChar(key);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        return getWrapped().getChar(key, defaultValue);
    }

    @Override
    public Character getChar(K key, Character defaultValue) {
        return getWrapped().getChar(key, defaultValue);
    }

    @Override
    public BigInteger getBigInteger(K key) {
        return getWrapped().getBigInteger(key);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        return getWrapped().getBigInteger(key, defaultValue);
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        return getWrapped().getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        return getWrapped().getBigDecimal(key, defaultValue);
    }

    @Override
    public Date getDate(K key) {
        return getWrapped().getDate(key);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        return getWrapped().getDate(key, defaultValue);
    }

}
