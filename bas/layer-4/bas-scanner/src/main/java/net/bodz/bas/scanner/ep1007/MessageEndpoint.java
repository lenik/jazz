package net.bodz.bas.scanner.ep1007;

import java.util.HashMap;
import java.util.Map;

public enum MessageEndpoint {

    HOST(0x04),

    DECODER(0x31),

    ;

    int code;

    private MessageEndpoint(int code) {
        this.code = code;
        Index.map.put(code, this);
    }

    public int getCode() {
        return code;
    }

    static class Index {
        static Map<Integer, MessageEndpoint> map = new HashMap<>();
    }

    public static MessageEndpoint forCode(int code) {
        return Index.map.get(code);
    }

}
