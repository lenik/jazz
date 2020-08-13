package net.bodz.bas.site;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ServiceMap
        extends LinkedHashMap<String, Object> {

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

}
