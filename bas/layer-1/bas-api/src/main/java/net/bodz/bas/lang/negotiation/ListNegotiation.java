package net.bodz.bas.lang.negotiation;

import java.util.Iterator;
import java.util.List;

public class ListNegotiation
        extends AbstractNegotiation {

    private List<IParameter> parameters;

    public ListNegotiation(List<IParameter> parameters) {
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
