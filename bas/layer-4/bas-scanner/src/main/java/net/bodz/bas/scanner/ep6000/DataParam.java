package net.bodz.bas.scanner.ep6000;

import java.util.HashMap;
import java.util.Map;

public enum DataParam {

    // ISO18000-6C commands

    /**
     * Reserved
     */
    Reserved(0x8000),

    ;

    public final int code;

    private DataParam(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    public int getCode() {
        return code;
    }

    static class Index {
        static Map<Integer, DataParam> map = new HashMap<>();
    }

    public static DataParam forCode(int code) {
        return Index.map.get(code);
    }

}
