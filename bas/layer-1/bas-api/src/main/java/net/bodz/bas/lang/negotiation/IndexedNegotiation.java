package net.bodz.bas.lang.negotiation;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IndexedNegotiation
        extends AbstractNegotiation {

    private List<IParameter> parameters;

    public IndexedNegotiation(final Object... typedParameters) {
        if (typedParameters == null)
            throw new NullPointerException("typedParameters");

        parameters = new AbstractList<IParameter>() {

            @Override
            public IParameter get(int index) {
                Object typedObj = typedParameters[index];
                return new Option(typedObj);
            }

            @Override
            public int size() {
                return typedParameters.length;
            }

        };
    }

    public IndexedNegotiation(IParameter... parameters) {
        this.parameters = Arrays.asList(parameters);
    }

    public IndexedNegotiation(List<IParameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int size() {
        return parameters.size();
    }

    @Override
    public Iterator<IParameter> iterator() {
        return parameters.iterator();
    }

    @Override
    public IParameter getParameter(String id) {
        for (IParameter p : this)
            if (p.is(id))
                return p;
        return null;
    }

}
