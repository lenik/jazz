package net.bodz.bas.scanner.ztx965n;

import java.util.HashMap;
import java.util.Map;

public enum TagType {

    /**
     * 操作成功
     */
    ISO18000_6B(1),

    EPCC1(2),

    EPCC1G2(4),

    EM4442(8),

    ;

    int code;

    private TagType(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    static class Index {
        static Map<Integer, TagType> map = new HashMap<>();
    }

    public static TagType forCode(int code) {
        return Index.map.get(code);
    }

}
