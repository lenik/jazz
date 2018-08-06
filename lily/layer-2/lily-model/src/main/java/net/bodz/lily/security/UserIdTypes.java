package net.bodz.lily.security;

import java.util.HashMap;
import java.util.Map;

public class UserIdTypes {

    static Map<Integer, UserIdType> idMap = new HashMap<>();

    static UserIdType def(int id, String label) {
        UserIdType type = new UserIdType();
        type.setId(id);
        type.setLabel(label);
        idMap.put(id, type);
        return type;
    }

    public static UserIdType forId(int id) {
        return idMap.get(id);
    }

    public static final UserIdType ALIPAY = def(10, "Alipay");
    public static final UserIdType WECHAT = def(11, "WeChat");
    public static final UserIdType QQ = def(12, "QQ");

}
