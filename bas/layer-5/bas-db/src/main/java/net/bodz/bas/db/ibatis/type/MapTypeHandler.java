package net.bodz.bas.db.ibatis.type;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.db.ibatis.AliasedType;

@AliasedType
public class MapTypeHandler
        extends AbstractMapTypeHandler<Map<String, Object>, String, Object> {

    @Override
    protected Map<String, Object> createMap() {
        return new LinkedHashMap<>();
    }

    @Override
    protected String parseKey(String str) {
        return str;
    }

    @Override
    protected Object parseValue(Object obj) {
        return obj;
    }

}
