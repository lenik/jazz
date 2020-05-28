package net.bodz.lily.security;

import java.util.HashMap;
import java.util.Map;

public class UserOtherIdTypes {

    static Map<Integer, UserOtherIdType> idMap = new HashMap<>();

    static UserOtherIdType def(int id, String label) {
        UserOtherIdType type = new UserOtherIdType();
        type.setId(id);
        type.setLabel(label);
        idMap.put(id, type);
        return type;
    }

    public static UserOtherIdType forId(int id) {
        return idMap.get(id);
    }

    public static final UserOtherIdType MOBILE = def(1, "mobile");
    public static final UserOtherIdType ALIPAY = def(10, "Alipay");
    public static final UserOtherIdType WECHAT = def(11, "WeChat");
    public static final UserOtherIdType QQ = def(12, "QQ");

}
