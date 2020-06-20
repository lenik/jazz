package net.bodz.bas.site.json;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.org.json.JsonStringer;
import net.bodz.bas.ui.dom1.UiValue;

public class JsonWrapper_jsonTest
        extends Assert {

    @Test
    public void test()
            throws Exception {
        JsonWrapper jw = JsonWrapper.wrap(true, "value");
        JsonWrapper_json v = new JsonWrapper_json();
        JsonStringer out = new JsonStringer();
        v.buildJsonView(null, out, UiValue.wrap(jw));
    }

}
