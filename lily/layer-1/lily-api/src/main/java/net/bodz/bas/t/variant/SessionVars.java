package net.bodz.bas.t.variant;

import java.util.Set;

import javax.servlet.http.HttpSession;

import net.bodz.bas.t.iterator.Iterators;

public class SessionVars
        extends AbstractVariantMap<String> {

    private final HttpSession session;

    public SessionVars(HttpSession session) {
        if (session == null)
            throw new NullPointerException("session");
        this.session = session;
    }

    @Override
    public Set<String> keySet() {
        return Iterators.toSet(session.getAttributeNames());
    }

    @Override
    public boolean containsKey(Object key) {
        String name = key.toString();
        return session.getAttribute(name) != null;
    }

    @Override
    public Object get(Object key) {
        String name = key.toString();
        return session.getAttribute(name);
    }

    public final ApplyFn fn = new ApplyFn();

    public class ApplyFn {

        public Byte applyByte(IVariantMap<String> src, String key, byte deleteValue) {
            Byte value = src.getByte(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getByte(key, null);
            return value;
        }

        public Short applyShort(IVariantMap<String> src, String key, short deleteValue) {
            Short value = src.getShort(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getShort(key, null);
            return value;
        }

        public Integer applyInt(IVariantMap<String> src, String key, int deleteValue) {
            Integer value = src.getInt(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getInt(key, null);
            return value;
        }

        public Long applyLong(IVariantMap<String> src, String key, long deleteValue) {
            Long value = src.getLong(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getLong(key, null);
            return value;
        }

        public Float applyFloat(IVariantMap<String> src, String key, float deleteValue) {
            Float value = src.getFloat(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getFloat(key, null);
            return value;
        }

        public Double applyDouble(IVariantMap<String> src, String key, double deleteValue) {
            Double value = src.getDouble(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getDouble(key, null);
            return value;
        }

        public Boolean applyBoolean(IVariantMap<String> src, String key, boolean deleteValue) {
            Boolean value = src.getBoolean(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getBoolean(key, null);
            return value;
        }

        public Character applyChar(IVariantMap<String> src, String key, char deleteValue) {
            Character value = src.getChar(key, null);
            if (value != null)
                if (value == deleteValue) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getChar(key, null);
            return value;
        }

        public String applyString(IVariantMap<String> src, String key, String deleteValue) {
            String value = src.getString(key, null);
            if (value != null)
                if (value.equals(deleteValue)) {
                    session.removeAttribute(key);
                    value = null;
                } else
                    session.setAttribute(key, value);
            else
                value = getString(key);
            return value;
        }

    }

}
