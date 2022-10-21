package net.bodz.bas.site;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ServiceMap
        extends LinkedHashMap<String, Object>
        implements
            IJsonForm {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(ServiceMap.class);

    public void install(Map<String, ?> map) {
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            install(key, entry.getValue());
        }
    }

    public boolean install(String key, Object service) {
        if (containsKey(key)) {
            Object existing = get(key);
            logger.errorf("Service key is already defined and skipped: %s of %s => %s", //
                    key, existing == null ? null : existing.getClass(), existing);
            return false;
        }
        put(key, service);
        return true;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String key : keySet()) {
            Object impl = get(key);
            if (impl == null)
                continue;
            out.key(key);
            out.object();
            out.entry("class", impl.getClass());
            out.endObject();
        }
    }

}
