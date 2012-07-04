package net.bodz.bas.disp.view;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.vfs.util.ContentTypes;

public abstract class AbstractHttpRenderer
        implements IHttpRenderer {

    @Override
    public String getContentType() {
        return ContentTypes.text_html.getName();
    }

    @Override
    public boolean render(Object object, INegotiation negotiation)
            throws NegotiationException, IOException {
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        for (IParameter p : negotiation) {
            if (p.idEquals(HttpServletRequest.class))
                req = p.cast(HttpServletRequest.class);
            else if (p.idEquals(HttpServletResponse.class))
                resp = p.cast(HttpServletResponse.class);
            else
                p.ignore();
        }
        return render(object, req, resp);
    }

}
