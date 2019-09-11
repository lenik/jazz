package net.bodz.bas.scanner.ep6000;

import java.util.HashMap;
import java.util.Map;

public enum CommandCode {

    // ISO18000-6C commands

    /**
     * 参数查询
     */
    GetParameter(0x16),

    SetParameter(0x17),

    StartDecode(0x18),

    StopDecode(0x19),

    ;

    int code;

    private CommandCode(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    public int getCode() {
        return code;
    }

    static class Index {
        static Map<Integer, CommandCode> map = new HashMap<Integer, CommandCode>();
    }

    public static CommandCode forCode(int code) {
        return Index.map.get(code);
    }

}
