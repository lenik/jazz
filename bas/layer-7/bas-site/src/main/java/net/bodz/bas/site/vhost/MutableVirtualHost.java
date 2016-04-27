package net.bodz.bas.site.vhost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.rst.AbstractElementHandler;
import net.bodz.bas.fmt.rst.ElementHandlerException;
import net.bodz.bas.fmt.rst.IElementHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.fmt.rst.IRstSerializable;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.std.ITyperFamily;

public class MutableVirtualHost
        extends MutableElement
        implements IVirtualHost, IRstSerializable {

    private static final long serialVersionUID = 1L;

    private List<HostSpecifier> hostSpecifiers = new ArrayList<HostSpecifier>();
    private Map<String, String> parameters = new TreeMap<String, String>();
    private Map<String, Object> attributes = new TreeMap<String, Object>();

    /** ⇱ Implementation Of {@link IVirtualHost}. */
    /* _____________________________ */static section.iface __VHOST__;

    @Override
    public List<HostSpecifier> getHostSpecifiers() {
        return hostSpecifiers;
    }

    public void addHostSpecifier(String hostName) {
        addHostSpecifier(hostName, 0, false);
    }

    public void addHostSpecifier(String hostName, int port, boolean includeSubDomains) {
        HostSpecifier hostSpecifier = new HostSpecifier(hostName, port, includeSubDomains);
        hostSpecifiers.add(hostSpecifier);
    }

    @Override
    public Iterable<String> getParameterNames() {
        return parameters.keySet();
    }

    @Override
    public String getParameter(String name) {
        return parameters.get(name);
    }

    @Override
    public void setParameter(String name, String value) {
        parameters.put(name, value);
    }

    @Override
    public void removeParameter(String name) {
        parameters.remove(name);
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        T value = (T) attributes.get(name);
        if (value == null)
            if (!attributes.containsKey(name))
                return defaultValue;
        return value;
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public ITyperFamily<?> getAttributeTypers(String attributeName) {
        throw new NotImplementedException();
    }

    @Override
    public void setAttributeTypers(String name, ITyperFamily<?> typers) {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IQueryable}. */
    /* _____________________________ */static section.iface __QUERY__;

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        Object val = getAttribute(specificationType.getName());
        if (val == null)
            return null;
        return (specificationType.cast(val));
    }

    @Override
    public Object query(String... args)
            throws QueryException {
        return null;
    }

    /** ⇱ Implementation Of {@link IRstSerializable}. */
    /* _____________________________ */static section.iface __RST__;

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        StringBuilder hostSpecBuf = new StringBuilder();
        for (HostSpecifier hostSpec : hostSpecifiers) {
            if (hostSpecBuf.length() != 0)
                hostSpecBuf.append(',');
            hostSpecBuf.append(hostSpec);
        }

        out.beginElement("host", getName());
        out.attribute("host-spec", hostSpecBuf.toString());

        for (Entry<String, ?> entry : attributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            out.attribute("attribute", key + "=" + value);
        }
        out.endElement();
    }

    @Override
    public IElementHandler getElementHandler() {
        return new EH();
    }

    class EH
            extends AbstractElementHandler {

        @Override
        public boolean attribute(String name, String data)
                throws ParseException, ElementHandlerException {
            attributes.put(name, data);
            return true;
        }

    }

}
