package net.bodz.bas.lang.negotiation;


public abstract class AbstractNegotiation
        implements INegotiation {

    @Override
    public INegotiation refine(INegotiation respond)
            throws NegotiationException {
        return null;
    }

    @Override
    public Parameter getParameter(Class<?> type) {
        String id = type.getCanonicalName();
        return getParameter(id);
    }

    @Override
    public <T> T get(Class<T> type) {
        String id = type.getCanonicalName();
        return get(id);
    }

    @Override
    public Parameter getParameter(String id) {
        for (Parameter p : this)
            if (p.is(id))
                return p;
        return null;
    }

    @Override
    public <T> T get(String id) {
        Parameter parameter = getParameter(id);
        if (parameter == null)
            return null;
        else
            return parameter.value();
    }

    @Override
    public void ignore()
            throws MandatoryException {
        for (Parameter p : this)
            p.ignore();
    }

    @Override
    public String toString() {
        StringBuilder buf = null;
        for (Parameter param : this) {
            if (buf == null)
                buf = new StringBuilder(this.size() * 100);
            else
                buf.append('\n');
            buf.append(param);
        }
        return buf.toString();
    }

}
