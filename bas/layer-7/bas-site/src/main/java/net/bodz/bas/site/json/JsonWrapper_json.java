package net.bodz.bas.site.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.ui.dom1.IUiRef;

public class JsonWrapper_json
        extends AbstractJsonViewBuilder<JsonWrapper> {

    public JsonWrapper_json() {
        super(JsonWrapper.class);
    }

    @Override
    protected void buildJsonView(IHttpViewContext ctx, IJsonOut out, IUiRef<JsonWrapper> ref)
            throws ViewBuilderException, IOException {
        JsonWrapper w = ref.get();

        if (w.key != null) {
            out.object();
            out.key(w.key);
        }

        BeanJsonDumper dumper = new BeanJsonDumper(out);
        dumper.setIncludeNull(w.includeNull);
        dumper.setIncludeFalse(w.includeFalse);
        dumper.depth(w.maxDepth);
        try {
            dumper.dump(w.obj, true);
        } catch (FormatException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }

        if (w.key != null) {
            out.endObject();
        }
    }

}
