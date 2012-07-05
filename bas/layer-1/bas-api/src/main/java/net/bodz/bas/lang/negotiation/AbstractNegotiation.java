package net.bodz.bas.lang.negotiation;

public abstract class AbstractNegotiation
        implements INegotiation {

    @Override
    public INegotiation refine(INegotiation respond)
            throws NegotiationException {
        return null;
    }

    @Override
    public IParameter getParameter(Class<?> type) {
        String id = type.getCanonicalName();
        return getParameter(id);
    }

    @Override
    public final <T> T get(Class<T> type) {
        String id = type.getCanonicalName();
        return get(id, null);
    }

    @Override
    public final <T> T get(Class<T> type, T defaultValue) {
        String id = type.getCanonicalName();
        return get(id, defaultValue);
    }

    @Override
    public final <T> T get(String id) {
        return get(id, null);
    }

    @Override
    public <T> T get(String id, T defaultValue) {
        IParameter parameter = getParameter(id);
        if (parameter == null)
            return defaultValue;
        else
            return parameter.getValue();
    }

    @Override
    public final <T> T require(Class<T> type)
            throws MandatoryException {
        return require(type, type.getCanonicalName());
    }

    @Override
    public final <T> T require(Class<T> type, String description)
            throws MandatoryException {
        String id = type.getCanonicalName();
        return require(id, description);
    }

    @Override
    public final <T> T require(String id)
            throws MandatoryException {
        return require(id, id);
    }

    @Override
    public <T> T require(String id, String description)
            throws MandatoryException {
        IParameter parameter = getParameter(id);
        if (parameter == null)
            return null;
        else
            return parameter.getValue();
    }

    @Override
    public void ignore()
            throws MandatoryException {
        for (IParameter p : this)
            p.ignore();
    }

    @Override
    public String toString() {
        StringBuilder buf = null;
        for (IParameter param : this) {
            if (buf == null)
                buf = new StringBuilder(this.size() * 100);
            else
                buf.append('\n');
            buf.append(param);
        }
        return buf.toString();
    }

}
