package net.bodz.bas.repr.state;

import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class StateJsonFn {

    public static State getFrom(JsonObject o, String key, State defaultValue) {
        Object val = o.get(key);
        State state = null;
        if (val instanceof String) {
            String name = (String) val;
            state = StateGroups.getState(name);
        } else if (val instanceof Number) {
            int stateKey = ((Number) val).intValue();
            state = StateGroups.getState(stateKey);
        }
        if (state == null)
            state = defaultValue;
        return state;
    }

}
