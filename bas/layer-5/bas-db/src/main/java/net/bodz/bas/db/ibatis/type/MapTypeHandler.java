package net.bodz.bas.db.ibatis.type;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import net.bodz.bas.db.ibatis.AliasedType;

@Alias("Map")
@AliasedType
public class MapTypeHandler
        extends AbstractMapTypeHandler<Map<String, Object>, String, Object> {

    @Override
    protected Map<String, Object> createMap() {
        return new LinkedHashMap<String, Object>();
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
