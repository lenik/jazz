package net.bodz.lily.util.ajax;

import java.io.IOException;

import org.json.JSONWriter;

import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.site.ajax.AbstractJsonViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;

public class JsonWrapper_json
        extends AbstractJsonViewBuilder<JsonWrapper> {

    public JsonWrapper_json() {
        super(JsonWrapper.class);
    }

    @Override
    protected void buildJsonView(IHttpViewContext ctx, JSONWriter out, IUiRef<JsonWrapper> ref)
            throws ViewBuilderException, IOException {
        JsonWrapper w = ref.get();

        if (w.key != null) {
            out.object();
            out.key(w.key);
        }

        BeanJsonDumper dumper = new BeanJsonDumper(out);
        dumper.depth(w.maxDepth);
        dumper.dump(w.obj);

        if (w.key != null) {
            out.endObject();
        }
    }

}
