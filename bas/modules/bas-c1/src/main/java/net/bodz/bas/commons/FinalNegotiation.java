package net.bodz.bas.commons;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.commons.exceptions.UnsupportedNegotiationException;

public class FinalNegotiation
        implements INegotiation {

    private List<NegotiationParameter> params;

    public FinalNegotiation(final Object... typedParameters) {
        if (typedParameters == null)
            throw new NullPointerException("typedParameters");

        params = new AbstractList<NegotiationParameter>() {

            @Override
            public NegotiationParameter get(int index) {
                return new NegotiationParameter(typedParameters[index]);
            }

            @Override
            public int size() {
                return typedParameters.length;
            }

        };
    }

    public FinalNegotiation(NegotiationParameter... parameters) {
        params = Arrays.asList(parameters);
    }

    public FinalNegotiation(Collection<NegotiationParameter> parameters) {
        params = new ArrayList<NegotiationParameter>(parameters);
    }

    public int getParameterCount() {
        return params.size();
    }

    public NegotiationParameter getParameter(int index) {
        return params.get(index);
    }

    @Override
    public Iterator<NegotiationParameter> iterator() {
        return params.iterator();
    }

    @Override
    public void bypass()
            throws UnsupportedNegotiationException {
        for (NegotiationParameter param : params)
            if (param.isMandatory())
                throw new UnsupportedNegotiationException(param);
    }

}
