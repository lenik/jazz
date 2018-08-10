package net.bodz.bas.scanner.ep1007;

import java.util.HashMap;
import java.util.Map;

public enum RxErrorCode {

    /**
     * 设置成功 应答
     */
    SetOK(0x06),

    /**
     * 设置失败 应答
     */
    SetFail(0x15),

    ;

    int code;

    private RxErrorCode(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    static class Index {
        static Map<Integer, RxErrorCode> map = new HashMap<>();
    }

    public static RxErrorCode forCode(int code) {
        return Index.map.get(code);
    }

}
