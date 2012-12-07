package net.bodz.bas.repr.view;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.vfs.util.ContentTypes;

public abstract class AbstractHttpRenderer
        implements IHttpRenderer {

    @Override
    public String getContentType() {
        return ContentTypes.text_html.getName();
    }

    @Override
    public boolean render(Object object, INegotiation negotiation)
            throws IOException {
        HttpServletRequest req = negotiation.get(HttpServletRequest.class);
        HttpServletResponse resp = negotiation.get(HttpServletResponse.class);
        return render(object, req, resp);
    }

}
