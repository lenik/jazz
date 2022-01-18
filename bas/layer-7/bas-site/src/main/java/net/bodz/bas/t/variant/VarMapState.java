package net.bodz.bas.t.variant;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.req.HttpSnap;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class VarMapState {

    public static <T extends IVarMapForm> T restoreFrom(T obj, HttpServletRequest req)
            throws ViewBuilderException {
        try {
            HttpSnap snap = (HttpSnap) req.getAttribute(HttpSnap.class.getName());
            if (snap == null)
                obj.readObject(VariantMaps.fromRequest(req));
            else
                obj.readObject(VariantMaps.fromParameterMap(snap.getParameterMap()));
        } catch (ParseException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        } catch (LoaderException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        return obj;
    }

}
