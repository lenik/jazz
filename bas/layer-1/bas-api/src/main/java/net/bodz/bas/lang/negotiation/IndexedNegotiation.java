package net.bodz.bas.lang.negotiation;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IndexedNegotiation
        extends AbstractNegotiation {

    private List<Parameter> parameters;

    public IndexedNegotiation(final Object... typedParameters) {
        if (typedParameters == null)
            throw new NullPointerException("typedParameters");

        parameters = new AbstractList<Parameter>() {

            @Override
            public Parameter get(int index) {
                Object typedObj = typedParameters[index];
                return new Option(typedObj);
            }

            @Override
            public int size() {
                return typedParameters.length;
            }

        };
    }

    public IndexedNegotiation(Parameter... parameters) {
        this.parameters = Arrays.asList(parameters);
    }

    public IndexedNegotiation(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int size() {
        return parameters.size();
    }

    @Override
    public Iterator<Parameter> iterator() {
        return parameters.iterator();
    }

}
