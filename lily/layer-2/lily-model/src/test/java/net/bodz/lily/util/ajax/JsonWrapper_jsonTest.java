package net.bodz.lily.util.ajax;

import org.json.JSONStringer;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ui.dom1.UiValue;

public class JsonWrapper_jsonTest
        extends Assert {

    @Test
    public void test()
            throws Exception {
        JsonWrapper jw = JsonWrapper.wrap(true);
        JsonWrapper_json v = new JsonWrapper_json();
        JSONStringer out = new JSONStringer();
        v.buildJsonView(null, out, UiValue.wrap(jw));
    }

}
