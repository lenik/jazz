package net.bodz.bas.scanner.ep6000;

import java.util.HashMap;
import java.util.Map;

public enum RxErrorCode {

    /**
     * 操作成功
     */
    OK(0),

    /**
     * 未知/不支持的命令
     */
    NoCommand(0xFF),

    /**
     * 校验码错误
     */
    ChecksumError(0xFE),

    /**
     * 命令正确但设备错误
     */
    DeviceError(0xFD),

    /**
     * 参数或者设置值错误
     */
    IllegalArgument(0xFC),

    ;

    int code;

    private RxErrorCode(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    static class Index {
        static Map<Integer, RxErrorCode> map = new HashMap<Integer, RxErrorCode>();
    }

    public static RxErrorCode forCode(int code) {
        return Index.map.get(code);
    }

}
