package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.html.io.BHtmlOut;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.form.SortOrder;

public class AjaxResult
        extends AbstractJsonResponse<AjaxResult> {

    static final Logger logger = LoggerFactory.getLogger(AjaxResult.class);

    Map<String, IHtmlOut> htmlUpdates;
    HtmlOutputFormat htmlOutputFormat;

    public AjaxResult() {
        this(SortOrder.KEEP);
    }

    public AjaxResult(SortOrder order) {
        super(0, null, null);
        htmlUpdates = order.newMap();
        htmlOutputFormat = new HtmlOutputFormat();
    }

    public IHtmlOut newUpdate(String id) {
        IHtmlOut out = htmlUpdates.get(id);
        if (out == null) {
            BHtmlOut htmlOut = new BHtmlOut(htmlOutputFormat);
            htmlUpdates.put(id, htmlOut);
        }
        return out;
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        super.writeObject(out);

        if (!htmlUpdates.isEmpty()) {
            for (IHtmlOut html : htmlUpdates.values())
                html.flush();
            out.key("updates");
            out.object();
            for (Map.Entry<String, IHtmlOut> entry : htmlUpdates.entrySet()) {
                out.key(entry.getKey());
                IHtmlOut htmlOut = entry.getValue();
                String html = htmlOut.toString();
                out.value(html);
            }
            out.endObject();
        }
    }

}
