package net.bodz.lily.api;

import java.util.HashMap;
import java.util.Map;

public class AppAccountTypes {

    static Map<Integer, AppAccountType> idMap = new HashMap<>();

    static AppAccountType def(int id, String label) {
        AppAccountType type = new AppAccountType();
        type.setId(id);
        type.setLabel(label);
        idMap.put(id, type);
        return type;
    }

    public static AppAccountType forId(int id) {
        return idMap.get(id);
    }

    public static final AppAccountType USE = def(1, "使用");
    public static final AppAccountType DEPOSIT = def(2, "充值");
    public static final AppAccountType WITHDRAW = def(3, "撤回");

}
