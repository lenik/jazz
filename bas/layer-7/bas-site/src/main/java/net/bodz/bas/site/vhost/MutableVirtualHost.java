package net.bodz.bas.site.vhost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.rst.AbstractRstHandler;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.bas.typer.std.ITyperFamily;

public class MutableVirtualHost
        extends MutableElement
        implements
            IVirtualHost,
            IRstForm,
            IJsonForm {

    private static final long serialVersionUID = 1L;

    private List<String> bindings = new ArrayList<String>();
    private Map<String, String> parameters = new TreeMap<String, String>();
    private Map<String, Object> attributes = new TreeMap<String, Object>();

    private ISiteRoot root;

    public MutableVirtualHost(ISiteRoot root) {
        this.root = root;
    }

    /** ⇱ Implementation Of {@link IVirtualHost}. */
    /* _____________________________ */static section.iface __VHOST__;

    @Override
    public List<String> getBindings() {
        return bindings;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public boolean hasParameter() {
        return !parameters.isEmpty();
    }

    @Override
    public Set<String> parameterNames() {
        return parameters.keySet();
    }

    @Override
    public boolean containsParameter(String name) {
        return parameters.containsKey(name);
    }

    @Override
    public String getParameter(String name) {
        return parameters.get(name);
    }

    @Override
    public String putParameter(String name, Object value) {
        return parameters.put(name, String.valueOf(value));
    }

    @Override
    public String removeParameter(String name) {
        return parameters.remove(name);
    }

    @Override
    public void removeAllParameters() {
        parameters.clear();
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        @SuppressWarnings("unchecked")
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

    @Override
    public ISiteRoot getRoot() {
        return root;
    }

    public void setRoot(ISiteRoot root) {
        this.root = root;
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

    /** ⇱ Implementation Of {@link IRstForm}. */
    /* _____________________________ */static section.iface __RST__;

    public String getHostSpec() {
        StringBuilder hostSpecBuf = new StringBuilder();
        for (String binding : bindings) {
            if (hostSpecBuf.length() != 0)
                hostSpecBuf.append(',');
            hostSpecBuf.append(binding);
        }
        return hostSpecBuf.toString();
    }

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        out.beginElement("host", getName());
        out.attribute("host-spec", getHostSpec());

        for (Entry<String, ?> entry : attributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            out.attribute("attribute", key + "=" + value);
        }
        out.endElement();
    }

    @Override
    public IRstHandler getElementHandler() {
        return new EH();
    }

    class EH
            extends AbstractRstHandler {

        @Override
        public boolean attribute(String name, String data)
                throws ParseException, ElementHandlerException {
            attributes.put(name, data);
            return true;
        }

    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("name", getName());
        out.entry("hostSpec", getHostSpec());
        out.entry("attributes", attributes);
    }

}
